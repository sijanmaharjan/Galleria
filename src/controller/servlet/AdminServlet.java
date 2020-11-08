package controller.servlet;

import app.AdminUser;
import app.AppContext;
import bean.remote.AppStatusBeanRemote;
import bean.remote.CategoryBeanRemote;
import bean.remote.RedemptionBeanRemote;
import bean.remote.UserBeanRemote;
import controller.servlet.decorator.IndexedDecorationApplier;
import controller.servlet.decorator.admin.AppStatsAttributeDecorator;
import controller.servlet.decorator.admin.CategoryInfoAttributeDecorator;
import controller.servlet.decorator.admin.RequestsInfoAttributeDecorator;
import controller.servlet.decorator.admin.UserInfoAttributeDecorator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "super-admin", urlPatterns = "/galleria.admin")
public class AdminServlet extends HttpServlet {
    @EJB
    private RedemptionBeanRemote redemptionBeanRemote;
    @EJB
    private UserBeanRemote userBeanRemote;
    @EJB
    private AppStatusBeanRemote appStatusBeanRemote;
    @EJB
    private CategoryBeanRemote categoryBeanRemote;

    private IndexedDecorationApplier indexedDecorationApplier;

    @Override
    public void init() throws ServletException {
        indexedDecorationApplier = new IndexedDecorationApplier(
                new AppStatsAttributeDecorator(appStatusBeanRemote),
                new CategoryInfoAttributeDecorator(categoryBeanRemote),
                new RequestsInfoAttributeDecorator(redemptionBeanRemote),
                new UserInfoAttributeDecorator(userBeanRemote)
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        HttpSession session = req.getSession();
        session.setAttribute(AppContext.USER_ID, AdminUser.UID);
        String p = Optional.ofNullable(req.getParameter("p")).orElse("a");
        req.setAttribute("p", p);
        indexedDecorationApplier.apply(req, appContext, p);
        req.getRequestDispatcher(appContext.getDirPrefix() + "admin/dash.jsp").forward(req, resp);
    }
}
