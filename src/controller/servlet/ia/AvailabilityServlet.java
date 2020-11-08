package controller.servlet.ia;

import app.AppContext;
import bean.remote.ImageBeanRemote;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.availability")
public class AvailabilityServlet extends HttpServlet {
    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String IID = req.getParameter("id");
        Boolean available = Boolean.valueOf(req.getParameter("available"));
        imageBeanRemote.toggleAvailability(IID, appContext.getUserID(), available);
    }
}
