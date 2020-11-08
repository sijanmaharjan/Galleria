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

@WebServlet(urlPatterns = "/galleria.rate")
public class RatingServlet extends HttpServlet {

    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        String IID = req.getParameter("id");
        String redirect = req.getParameter("redirect");
        String pnt = req.getParameter("point");
        if(pnt == null) {
            imageBeanRemote.unrateImage(IID, UID);
        }else{
            if(!imageBeanRemote.checkRated(IID, UID)){
                int point = Integer.parseInt(pnt);
                imageBeanRemote.rateImage(IID, UID, point);
            }
        }
        resp.sendRedirect(redirect);
    }
}
