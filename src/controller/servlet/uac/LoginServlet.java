package controller.servlet.uac;

import app.AppContext;
import bean.entity.User;
import bean.remote.UserBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/galleria.login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserBeanRemote userBeanRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String homeUri = req.getServletContext().getInitParameter(AppContext.HOME_URI);
        String redirect = Optional.of(req.getParameter("redirect")).orElse(homeUri);
        User user = userBeanRemote.login(req.getParameter("username"), req.getParameter("password"));
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute(AppContext.USER_ID, user.getId());
        } else {
            ServletContext context = req.getServletContext();
            context.setAttribute("error", "Username or Password Incorrect");
            context.setAttribute("status", 402);
        }
        resp.sendRedirect(redirect);
    }
}