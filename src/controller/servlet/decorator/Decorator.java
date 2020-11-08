package controller.servlet.decorator;

import app.AppContext;

import javax.servlet.http.HttpServletRequest;

public interface Decorator {
    void decorate(HttpServletRequest request, AppContext appContext);
}
