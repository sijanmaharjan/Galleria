package controller.servlet.decorator.dash;

import app.AppContext;
import bean.remote.TransactionBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;

public class StatementAttributeDecorator extends IndexedRequestDecorator {

    final public static String id = "b";

    private final TransactionBeanRemote transactionBeanRemote;

    public StatementAttributeDecorator(TransactionBeanRemote transactionBeanRemote) {
        super(id);
        this.transactionBeanRemote = transactionBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest request, AppContext appContext) {
        request.setAttribute("zone", ZoneId.systemDefault());
        request.setAttribute("statements", transactionBeanRemote.getStatements(appContext.getUserID()));
    }
}
