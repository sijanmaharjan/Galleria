package controller.servlet.ia;

import app.AppContext;
import bean.entity.Image;
import bean.remote.ImageBeanRemote;
import controller.service.ImageFileService;
import controller.service.ImageFileServiceImpl;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/galleria.delete(image)")
public class DeleteServlet extends HttpServlet {

    @EJB
    private ImageBeanRemote imageBeanRemote;

    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String IID = req.getParameter("id");
        String redirect = req.getParameter("redirect");
        Image image = imageBeanRemote.find(IID, appContext.getUserID());
        if(image != null) {
            imageBeanRemote.deleteImage(IID);
            imageFileService.removeFile(req, image);
        }
        resp.sendRedirect(Optional.ofNullable(redirect).orElse("galleria.dash?p=c"));
    }
}
