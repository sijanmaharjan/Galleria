package controller.servlet.decorator.admin;

import app.AppContext;
import bean.remote.AppStatusBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;

public class AppStatsAttributeDecorator extends IndexedRequestDecorator {

    final static public String id = "a";

    private final AppStatusBeanRemote appStatusBeanRemote;

    public AppStatsAttributeDecorator(AppStatusBeanRemote appStatusBeanRemote) {
        super(id);
        this.appStatusBeanRemote = appStatusBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest request, AppContext appContext) {
        request.setAttribute("app_status", appStatusBeanRemote.getAppStatus());
        request.setAttribute("mostly_liked_images", appStatusBeanRemote.getMostlyLikedImages());
        request.setAttribute("highly_rated_images", appStatusBeanRemote.getHighlyRatedImages());
        request.setAttribute("mostly_sold_images", appStatusBeanRemote.getMostlySoldImages());
        request.setAttribute("most_expensive_images", appStatusBeanRemote.getMostExpensiveImages());
        request.setAttribute("top_earning_accounts", appStatusBeanRemote.getTopEarningAccounts());
        request.setAttribute("top_rated_accounts", appStatusBeanRemote.getTopRatedAccounts());
    }
}
