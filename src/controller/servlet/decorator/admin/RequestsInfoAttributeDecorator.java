package controller.servlet.decorator.admin;

import app.AppContext;
import bean.remote.RedemptionBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;

public class RequestsInfoAttributeDecorator extends IndexedRequestDecorator {

    final static public String id = "c";
    private final RedemptionBeanRemote redemptionBeanRemote;

    public RequestsInfoAttributeDecorator(RedemptionBeanRemote redemptionBeanRemote) {
        super(id);
        this.redemptionBeanRemote = redemptionBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest req, AppContext appContext) {
        req.setAttribute("zone2", ZoneId.systemDefault());
        req.setAttribute("requests", redemptionBeanRemote.getPendingRequests());
    }
}
