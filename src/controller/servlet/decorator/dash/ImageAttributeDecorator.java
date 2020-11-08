package controller.servlet.decorator.dash;

import app.AppContext;
import bean.remote.CategoryBeanRemote;
import bean.remote.ImageBeanRemote;
import bean.remote.UserBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;

public class ImageAttributeDecorator extends IndexedRequestDecorator {

    final public static String id = "c";

    private final UserBeanRemote userBeanRemote;
    private final CategoryBeanRemote categoryBeanRemote;
    private final ImageBeanRemote imageBeanRemote;

    public ImageAttributeDecorator(UserBeanRemote userBeanRemote, CategoryBeanRemote categoryBeanRemote, ImageBeanRemote imageBeanRemote) {
        super(id);
        this.userBeanRemote = userBeanRemote;
        this.categoryBeanRemote = categoryBeanRemote;
        this.imageBeanRemote = imageBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest request, AppContext appContext) {
        String UID = appContext.getUserID();
        request.setAttribute("user", userBeanRemote.getDetails(UID));
        request.setAttribute("zone2", ZoneId.systemDefault());
        request.setAttribute("categories", categoryBeanRemote.getList());
        request.setAttribute("images", imageBeanRemote.listImages(UID));
    }
}
