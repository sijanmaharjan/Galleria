package controller.servlet.decorator.admin;

import app.AppContext;
import bean.remote.UserBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;

public class UserInfoAttributeDecorator extends IndexedRequestDecorator {

    final static public String id = "b";

    private final UserBeanRemote userBeanRemote;

    public UserInfoAttributeDecorator(UserBeanRemote userBeanRemote) {
        super(id);
        this.userBeanRemote = userBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest req, AppContext appContext) {
        req.setAttribute("zone", ZoneId.systemDefault());
        req.setAttribute("users", userBeanRemote.listUsers());
    }
}
