package controller.servlet.front.home.userview;

import app.AppContext;
import bean.remote.WishBeanRemote;
import bean.view.Wish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WishlistView extends UserViewDispatcher.ImageView {

    final public static String page_id = "d";

    private final WishBeanRemote wishBeanRemote;

    public WishlistView(WishBeanRemote wishBeanRemote) {
        super(page_id);
        this.wishBeanRemote = wishBeanRemote;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext, UserViewDispatcher.ImageRequest imageRequest) throws ServletException, IOException
    {
        List<Wish> wishes = wishBeanRemote.getWishlist(appContext.getUserID());
        req.setAttribute("wishes", wishes);
        req.getRequestDispatcher(appContext.getDirPrefix() + super.jsp_file).forward(req, resp);
    }
}
