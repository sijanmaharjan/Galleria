package controller.servlet.front.home;

import app.AppContext;
import controller.servlet.front.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeDispatcher {

    private final View userView;
    private final View publicView;

    public HomeDispatcher(View userView, View publicView) {
        this.userView = userView;
        this.publicView = publicView;
    }

    public void dispatch(HttpServletRequest req, HttpServletResponse resp, AppContext appContext) throws ServletException, IOException {
        if(appContext.isUserLoggedIn()){
            userView.doGet(req, resp, appContext);
        }else{
            publicView.doGet(req, resp, appContext);
        }
    }
}
