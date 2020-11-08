package controller.servlet.ia;

import app.AppContext;
import bean.entity.Image;
import bean.remote.BankDetailBeanRemote;
import bean.remote.CategoryBeanRemote;
import bean.remote.ImageBeanRemote;
import controller.service.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/galleria.update(image)")
public class UpdateServlet extends HttpServlet {

    @EJB
    private ImageBeanRemote imageBeanRemote;
    @EJB
    private CategoryBeanRemote categoryBeanRemote;
    @EJB
    private BankDetailBeanRemote bankDetailBeanRemote;

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
        Image expected = imageBeanRemote.find(req.getParameter("id"), appContext.getUserID());
        if(expected != null){
            Image image = new Image();
            image.setId(expected.getId());
            image.setAvailable(expected.getAvailable());
            image.setCategory(categoryBeanRemote.getOrCreate(req.getParameter("category")));
            image.setTitle(req.getParameter("title"));
            image.setDescription(req.getParameter("description"));
            image.setPrice(Double.parseDouble(req.getParameter("price")));
            image.setSource(expected.getSource());
            image.setUser(expected.getUser());
            image.setUploadTime(expected.getUploadTime());
            imageBeanRemote.updateImageDetail(image);

            if((expected.getPrice() == 0 && image.getPrice() > 0)||(expected.getPrice() > 0 && image.getPrice() == 0)) {
                imageFileService.toggleWatermark(req, image);
            }

            if(req.getParameter("bank") != null) {
                bankDetailService.createOrUpdateDetails(
                        appContext.getUser(),
                        req.getParameter("bank"),
                        req.getParameter("acName"),
                        req.getParameter("acNumber")
                );
            }
        }
        resp.sendRedirect(Optional.ofNullable(req.getParameter("redirect")).orElse("galleria.dash?p=c"));
    }
}
