package controller.servlet.ia;

import app.AppContext;
import bean.entity.Image;
import bean.remote.BankDetailBeanRemote;
import bean.remote.CategoryBeanRemote;
import bean.remote.ImageBeanRemote;
import controller.service.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet(urlPatterns = "/galleria.new(image)")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class ImageUploadServlet extends HttpServlet {

    @EJB
    private ImageBeanRemote imageBeanRemote;
    @EJB
    private BankDetailBeanRemote bankDetailBeanRemote;
    @EJB
    private CategoryBeanRemote categoryBeanRemote;

    private ImageFileService imageFileService;
    private BankDetailService bankDetailService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl(
                new ThumbnailServiceImpl()
        );
        this.bankDetailService = new BankDetailServiceImpl(bankDetailBeanRemote);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String IID = UUID.randomUUID().toString();
        double price = Double.parseDouble(req.getParameter("price"));
        String fileName = IID + ".jpeg";
        Image image = new Image();
        image.setId(IID);
        image.setAvailable(true);
        image.setCategory(categoryBeanRemote.getOrCreate(req.getParameter("category")));
        image.setTitle(req.getParameter("title"));
        image.setDescription(req.getParameter("description"));
        image.setPrice(price);
        image.setSource(fileName);
        image.setUser(appContext.getUser());
        image.setUploadTime(new Date());

        imageFileService.uploadFile(req, image, req.getPart("imageFile"));
        imageBeanRemote.addNewImage(image);

        if(req.getParameter("bank") != null) {
            bankDetailService.createOrUpdateDetails(
                    appContext.getUser(),
                    req.getParameter("bank"),
                    req.getParameter("acName"),
                    req.getParameter("acNumber")
            );
        }

        resp.sendRedirect("galleria.dash?p=c");
    }
}
