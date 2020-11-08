package controller.servlet.front;

public abstract class AbstractView implements View{
    private final String viewId;

    public AbstractView(String pageId) {
        this.viewId = pageId;
    }

    public String getViewId() {
        return viewId;
    }
}
