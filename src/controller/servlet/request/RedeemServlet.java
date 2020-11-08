package controller.servlet.request;

import app.AppContext;
import bean.remote.UserBeanRemote;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/galleria.submit(redeem_request)")
public class RedeemServlet extends HttpServlet {

    @EJB
    private UserBeanRemote userBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AppContext appContext = AppContext.parseContext(req.getServletContext());
            userBeanRemote.createRedeemRequest(appContext.getUserID());
        }catch (EJBException e){
            String msg = e.getCause().getCause().getMessage();
            if(msg.contains("java.sql.SQLException")){
                String errorMsg = msg.split("\n")[1].split("java.sql.SQLException: ")[1];
                resp.setContentType("text/plain");
                PrintWriter out = resp.getWriter();
                out.print(errorMsg);
            } else throw e;
        }
    }
}
