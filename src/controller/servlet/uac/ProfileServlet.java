package controller.servlet.uac;

import app.AppContext;
import bean.entity.User;
import bean.entity.option.Gender;
import bean.remote.UserBeanRemote;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.update(profile)")
public class ProfileServlet extends HttpServlet {

    @EJB
    private UserBeanRemote userBeanRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        String redirect = req.getParameter("redirect");
        User modified = new User(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("birthDate"),
                Gender.valueOf(req.getParameter("gender")),
                req.getParameter("email"),
                req.getParameter("username"),
                ""
        );
        modified.setId(UID);
        String password = req.getParameter("newPsw");
        User expected = userBeanRemote.find(UID);
        modified.setEarned(expected.getEarned());
        if(password == null || password.trim().isEmpty()) {
            modified.setPassword(expected.getPassword());
            userBeanRemote.updateUserDetail(modified);
        }else{
            String oldPassword = req.getParameter("oldPsw");
            String confirm = req.getParameter("confirm");
            ServletContext context = req.getServletContext();
            if(!password.equals(confirm)){
                context.setAttribute("error", "Cannot confirm password");
            }else if(userBeanRemote.checkPassword(UID, oldPassword)){
                context.setAttribute("error", "Failed to change password.");
                session.removeAttribute("UID");
            }else{
                modified.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
                userBeanRemote.updateUserDetail(modified);
            }
        }
        resp.sendRedirect(redirect);
    }
}
