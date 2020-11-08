package controller.servlet.category;
import bean.entity.Image;
import bean.remote.CategoryBeanRemote;
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
import java.util.List;

@WebServlet(urlPatterns = "/galleria.admin.delete(category)")
public class DeleteServlet extends HttpServlet {

    @EJB
    private CategoryBeanRemote categoryBeanRemote;
    @EJB
    private ImageBeanRemote imageBeanRemote;

    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Image> images = imageBeanRemote.listByCategoryId(req.getParameter("id"));
        imageFileService.removeFiles(req, images);
        categoryBeanRemote.delete(req.getParameter("id"));
    }
}
