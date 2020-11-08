package controller.servlet;

import app.AppContext;
import bean.remote.*;
import controller.servlet.decorator.IndexedDecorationApplier;
import controller.servlet.decorator.dash.BankDetailAttributeDecorator;
import controller.servlet.decorator.dash.DashAttributeDecorator;
import controller.servlet.decorator.dash.ImageAttributeDecorator;
import controller.servlet.decorator.dash.StatementAttributeDecorator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/galleria.dash")
public class DashboardServlet extends HttpServlet {

    @EJB
    private TransactionBeanRemote transactionBeanRemote;
    @EJB
    private UserBeanRemote userBeanRemote;
    @EJB
    private AppStatusBeanRemote appStatusBeanRemote;
    @EJB
    private BankDetailBeanRemote bankDetailBeanRemote;
    @EJB
    private ImageBeanRemote imageBeanRemote;
    @EJB
    private CategoryBeanRemote categoryBeanRemote;

    private IndexedDecorationApplier indexedDecorationApplier;

    @Override
    public void init() throws ServletException {
        indexedDecorationApplier = new IndexedDecorationApplier(
                new DashAttributeDecorator(userBeanRemote, appStatusBeanRemote),
                new StatementAttributeDecorator(transactionBeanRemote),
                new ImageAttributeDecorator(userBeanRemote, categoryBeanRemote, imageBeanRemote),
                new BankDetailAttributeDecorator(bankDetailBeanRemote)
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String p = Optional.ofNullable(req.getParameter("p")).orElse("a");
        req.setAttribute("p", p);
        indexedDecorationApplier.apply(req, appContext, p);
        req.getRequestDispatcher(appContext.getDirPrefix()+"home/dashboard.jsp").forward(req, resp);
    }
}
