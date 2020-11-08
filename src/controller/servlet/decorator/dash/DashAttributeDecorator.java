package controller.servlet.decorator.dash;

import app.AppContext;
import bean.remote.AppStatusBeanRemote;
import bean.remote.UserBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;

public class DashAttributeDecorator extends IndexedRequestDecorator {

    final public static String id = "a";

    private final UserBeanRemote userBeanRemote;
    private final AppStatusBeanRemote appStatusBeanRemote;

    public DashAttributeDecorator(UserBeanRemote userBeanRemote, AppStatusBeanRemote appStatusBeanRemote) {
        super(id);
        this.userBeanRemote = userBeanRemote;
        this.appStatusBeanRemote = appStatusBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest request, AppContext appContext) {
        String UID = appContext.getUserID();
        request.setAttribute("user", userBeanRemote.getDetails(UID));
        request.setAttribute("mostly_liked_images", appStatusBeanRemote.getMostlyLikedImages(UID));
        request.setAttribute("highly_rated_images", appStatusBeanRemote.getHighlyRatedImages(UID));
        request.setAttribute("mostly_sold_images", appStatusBeanRemote.getMostlySoldImages(UID));
    }
}
