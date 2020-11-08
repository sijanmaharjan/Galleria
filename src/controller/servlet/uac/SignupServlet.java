package controller.servlet.uac;

import app.AppContext;
import bean.entity.User;
import bean.entity.option.Gender;
import org.mindrot.jbcrypt.BCrypt;
import bean.remote.UserBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.signup")
public class SignupServlet extends HttpServlet {

    @EJB
    private UserBeanRemote userBeanRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getParameter("password").equals(req.getParameter("confirm"))) {
            User user = new User(
                    req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    req.getParameter("birthDate"),
                    Gender.valueOf(req.getParameter("gender")),
                    req.getParameter("email"),
                    req.getParameter("username"),
                    BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt(12))
            );
            userBeanRemote.register(user);
            HttpSession session = req.getSession();
            session.setAttribute(AppContext.USER_ID, user.getId());
        }else{
            ServletContext context = req.getServletContext();
            context.setAttribute("error", "Password didn't match");
        }
        resp.sendRedirect("galleria.oh");
    }
}
