package controller.servlet.notification;

import app.AppContext;
import bean.entity.Notification;
import bean.remote.NotificationBeanRemote;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/galleria.messages")
public class NotificationViewServlet extends HttpServlet {
    @EJB
    private NotificationBeanRemote notificationBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        List<Notification> messages = notificationBeanRemote.getMessages(UID);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher(appContext.getDirPrefix()+"notification.jsp").forward(req, resp);
    }
}
