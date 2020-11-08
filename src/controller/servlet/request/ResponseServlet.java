package controller.servlet.request;

import bean.remote.RedemptionBeanRemote;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.admin.response(request)")
public class ResponseServlet extends HttpServlet {

    @EJB
    private RedemptionBeanRemote redemptionBeanRemote;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String response = req.getParameter("response");
        redemptionBeanRemote.response(id, response);
    }
}
