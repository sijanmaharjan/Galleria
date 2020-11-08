package controller.servlet.category;


import bean.entity.Category;
import bean.remote.CategoryBeanRemote;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.admin.update(category)")
public class UpdateServlet extends HttpServlet {
    @EJB
    private CategoryBeanRemote categoryBeanRemote;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryBeanRemote.update(new Category(req.getParameter("id"), req.getParameter("title")));
    }
}