package controller.servlet.decorator;

public abstract class IndexedRequestDecorator implements Decorator {

    private final String id;

    public IndexedRequestDecorator(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
