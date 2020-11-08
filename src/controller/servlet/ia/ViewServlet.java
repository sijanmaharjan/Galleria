package controller.servlet.ia;

import app.AdminUser;
import app.AppContext;
import bean.TempImage;
import bean.entity.Image;
import bean.remote.ImageBeanRemote;
import bean.view.DetailedImage;
import com.google.gson.Gson;
import controller.taglib.Rating;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/galleria.view(image)")
public class ViewServlet extends HttpServlet {
    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        String IID = req.getParameter("id");
        Image image = imageBeanRemote.get(IID);
        DetailedImage detailedImage = imageBeanRemote.findImageDetail(UID, IID);
        TempImage tempImage = new TempImage(
                image.getId(),
                image.getTitle(),
                image.getSource(),
                image.getDescription(),
                new Rating().getRating(detailedImage.getRatings()),
                detailedImage.getFavourite(),
                detailedImage.getFree(),
                UID.equals(AdminUser.UID) || (imageBeanRemote.isOwner(IID, UID) || imageBeanRemote.isAccessible(IID, UID)),
                detailedImage.getPrice()
        );
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(tempImage));
    }
}
