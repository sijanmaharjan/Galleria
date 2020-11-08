package controller.servlet.ia;

import bean.TempImage;
import bean.entity.Image;
import bean.remote.ImageBeanRemote;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/galleria.admin.view(image)")
public class AdminViewServlet extends HttpServlet {
    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String IID = req.getParameter("id");
        Image image = imageBeanRemote.get(IID);
        TempImage tempImage = new TempImage(
                image.getId(),
                image.getTitle(),
                image.getSource(),
                image.getDescription(),
                "",
                false,
                true,
                true,
                image.getPrice()
        );
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(tempImage));
    }
}
