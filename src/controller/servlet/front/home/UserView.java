package controller.servlet.front.home;

import app.AppContext;
import bean.entity.Category;
import bean.remote.CategoryBeanRemote;
import bean.remote.ImageBeanRemote;
import bean.remote.WishBeanRemote;
import controller.servlet.front.View;
import controller.servlet.front.home.userview.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserView implements View {

    private UserViewDispatcher viewDispatcher;

    private CategoryBeanRemote categoryBeanRemote;

    public UserView() {}

    public void init(CategoryBeanRemote categoryBeanRemote,
                     ImageBeanRemote imageBeanRemote,
                     WishBeanRemote wishBeanRemote){
        this.categoryBeanRemote = categoryBeanRemote;
        this.viewDispatcher = new UserViewDispatcher(
                new AllImageView(imageBeanRemote),
                new MyImageView(imageBeanRemote),
                new FavouriteImageView(imageBeanRemote),
                new WishlistView(wishBeanRemote),
                new PurchasedImageView(imageBeanRemote)
        );
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext) throws ServletException, IOException {
        String p = Optional.ofNullable(req.getParameter("p")).orElse("a");
        UserViewDispatcher.ImageRequest imageRequest = new UserViewDispatcher.ImageRequest(
                req.getParameter("s"),
                req.getParameter("c"),
                (req.getParameter("f") == null)? null : ImageBeanRemote.Filter.valueOf(req.getParameter("f")),
                req.getParameter("min") == null? null : Double.parseDouble(req.getParameter("min")),
                req.getParameter("max") == null? null : Double.parseDouble(req.getParameter("max"))
        );
        req.setAttribute("p", p);
        req.setAttribute("s", imageRequest.getSearchText());
        req.setAttribute("c", imageRequest.getCategoryId());
        req.setAttribute("f", imageRequest.getFilter() != null? imageRequest.getFilter().toString() : null);
        req.setAttribute("min", imageRequest.getMin());
        req.setAttribute("max", imageRequest.getMax());
        if(!p.equals("d")) {
            List<Category> categories = categoryBeanRemote.getList();
            req.setAttribute("categories", categories);
        }
        req.setAttribute("user", appContext.getDetailedUser());
        viewDispatcher.dispatch(req, resp, appContext, imageRequest);
    }
}
