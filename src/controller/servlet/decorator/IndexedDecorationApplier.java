package controller.servlet.decorator;

import app.AppContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexedDecorationApplier {

    private final List<IndexedRequestDecorator> decorators = new ArrayList<>();

    public IndexedDecorationApplier(IndexedRequestDecorator ... decorators){
        this.decorators.addAll(Arrays.asList(decorators));
    }

    public void apply(HttpServletRequest request, AppContext appContext, String index){
        decorators.stream().filter(d->d.getId().equals(index)).findFirst().orElse(decorators.get(0)).decorate(request, appContext);
    }

}
