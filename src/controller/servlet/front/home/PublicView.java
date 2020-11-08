package controller.servlet.front.home;

import app.AppContext;
import app.MockImages;
import bean.entity.Image;
import bean.remote.ImageBeanRemote;
import controller.servlet.front.View;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PublicView implements View {

    private final String jspFile = "index.jsp";
    private ImageBeanRemote imageBeanRemote;

    public void init(ImageBeanRemote imageBeanRemote) {
        this.imageBeanRemote = imageBeanRemote;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext) throws ServletException, IOException {
        List<Image> images = imageBeanRemote.getShowcaseImages();
        images = (images.size() >= 3)? images : MockImages.get();
        req.setAttribute("images", images);
        req.getRequestDispatcher(appContext.getDirPrefix() + jspFile).forward(req, resp);
    }
}
