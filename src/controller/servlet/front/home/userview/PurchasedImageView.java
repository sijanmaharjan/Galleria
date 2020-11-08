package controller.servlet.front.home.userview;

import app.AppContext;
import bean.remote.ImageBeanRemote;
import bean.view.DetailedImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PurchasedImageView extends UserViewDispatcher.ImageView {

    final public static String page_id = "e";

    private final ImageBeanRemote imageBeanRemote;

    public PurchasedImageView(ImageBeanRemote imageBeanRemote) {
        super(page_id);
        this.imageBeanRemote = imageBeanRemote;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext, UserViewDispatcher.ImageRequest imageRequest) throws ServletException, IOException
    {
        List<DetailedImage> purchased = imageBeanRemote.getPurchasedImages(appContext.getUserID());
        req.setAttribute("images", purchased);
        req.getRequestDispatcher(appContext.getDirPrefix() + super.jsp_file).forward(req, resp);
    }
}
