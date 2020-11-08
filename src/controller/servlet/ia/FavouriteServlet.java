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

@WebServlet(urlPatterns = "/galleria.favourite")
public class FavouriteServlet extends HttpServlet {

    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        String IID = req.getParameter("id");
        String remark = req.getParameter("remark");
        if(remark == null || remark.equals("like")) {
            imageBeanRemote.markAsLiked(IID, UID);
        }else{
            imageBeanRemote.markAsDisliked(IID, UID);
        }
    }
}
