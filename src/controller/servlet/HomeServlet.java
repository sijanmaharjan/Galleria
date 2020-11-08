package controller.servlet;

import app.AppContext;
import bean.remote.CategoryBeanRemote;
import bean.remote.ImageBeanRemote;
import bean.remote.WishBeanRemote;
import controller.servlet.front.home.HomeDispatcher;
import controller.servlet.front.home.PublicView;
import controller.servlet.front.home.UserView;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.oh")
public class HomeServlet extends HttpServlet {

    private final HomeDispatcher dispatcher;
    private final PublicView publicView;
    private final UserView userView;

    @EJB
    private ImageBeanRemote imageBeanRemote;
    @EJB
    private CategoryBeanRemote categoryBeanRemote;
    @EJB
    private WishBeanRemote wishBeanRemote;

    public HomeServlet() {
        userView = new UserView();
        publicView = new PublicView();
        this.dispatcher = new HomeDispatcher(userView, publicView);
    }

    @Override
    public void init() throws ServletException {
        publicView.init(imageBeanRemote);
        userView.init(categoryBeanRemote, imageBeanRemote, wishBeanRemote);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.dispatch(req, resp, AppContext.parseContext(req.getServletContext()));
    }
}
