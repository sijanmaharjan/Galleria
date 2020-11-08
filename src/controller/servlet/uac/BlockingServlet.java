package controller.servlet.uac;

import bean.remote.UserBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.admin.block(user)")
public class BlockingServlet extends HttpServlet {

    @EJB
    private UserBeanRemote userBeanRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String UID = req.getParameter("_id");
        boolean block = Boolean.parseBoolean(req.getParameter("block"));
        if(block) {
            userBeanRemote.blockUser(UID);
        }else {
            userBeanRemote.allowUser(UID);
        }
    }
}
