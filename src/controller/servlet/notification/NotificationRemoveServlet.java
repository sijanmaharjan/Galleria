package controller.servlet.notification;

import app.AppContext;
import bean.remote.NotificationBeanRemote;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.remove(message)")
public class NotificationRemoveServlet extends HttpServlet {
    @EJB
    private NotificationBeanRemote notificationBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        String NID = req.getParameter("id");
        if(NID != null) notificationBeanRemote.removeMessage(UID, Integer.parseInt(NID));
        resp.sendRedirect("galleria.messages");
    }
}
