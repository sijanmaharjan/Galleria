package controller.servlet.uac;

import app.AppContext;
import bean.remote.ImageBeanRemote;
import bean.remote.UserBeanRemote;
import controller.service.ImageFileService;
import controller.service.ImageFileServiceImpl;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.remove(user.account)")
public class AccountRemovalServlet extends HttpServlet {

    @EJB
    private UserBeanRemote userBeanRemote;
    @EJB
    private ImageBeanRemote imageBeanRemote;

    private ImageFileService imageFileService;

    @Override
    public void init() throws ServletException {
        this.imageFileService = new ImageFileServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String UID = appContext.getUserID();
        String psw = req.getParameter("password");
        if(userBeanRemote.checkPassword(UID, psw)){
            imageFileService.removeFiles(req, imageBeanRemote.listImages(UID));
            userBeanRemote.deleteUser(UID);
        }else{
            ServletContext context = req.getServletContext();
            context.setAttribute("error", "Failed to delete account.");
        }
        session.removeAttribute(AppContext.USER_ID);
        resp.sendRedirect("galleria.oh");
    }
}
