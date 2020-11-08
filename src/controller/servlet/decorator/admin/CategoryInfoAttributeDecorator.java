package controller.servlet.decorator.admin;

import app.AppContext;
import bean.remote.CategoryBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;

public class CategoryInfoAttributeDecorator extends IndexedRequestDecorator {

    final static public String id = "d";

    private final CategoryBeanRemote categoryBeanRemote;

    public CategoryInfoAttributeDecorator(CategoryBeanRemote categoryBeanRemote) {
        super(id);
        this.categoryBeanRemote = categoryBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest req, AppContext appContext) {
        req.setAttribute("categories", categoryBeanRemote.getList());
    }
}
