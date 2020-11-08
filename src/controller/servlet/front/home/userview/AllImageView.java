package controller.servlet.front.home.userview;

import app.AppContext;
import bean.remote.ImageBeanRemote;
import bean.view.DetailedImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllImageView extends UserViewDispatcher.ImageView {

    final public static String page_id = "a";

    private final ImageBeanRemote imageBeanRemote;

    public AllImageView(ImageBeanRemote imageBeanRemote) {
        super(page_id);
        this.imageBeanRemote = imageBeanRemote;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext, UserViewDispatcher.ImageRequest imageRequest) throws ServletException, IOException {
        List<DetailedImage> images = imageBeanRemote.getDetailedImages(
                appContext.getUserID(),
                imageRequest.getCategoryId(),
                imageRequest.getSearchText(),
                imageRequest.getFilter(),
                imageRequest.getMin(),
                imageRequest.getMax()
        );
        req.setAttribute("images", images);
        req.getRequestDispatcher(appContext.getDirPrefix() + super.jsp_file).forward(req, resp);
    }
}
