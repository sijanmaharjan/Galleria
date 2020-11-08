package controller.servlet.decorator.dash;

import app.AppContext;
import bean.entity.BankDetail;
import bean.remote.BankDetailBeanRemote;
import controller.servlet.decorator.IndexedRequestDecorator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class BankDetailAttributeDecorator extends IndexedRequestDecorator {

    final public static String id = "d";

    private final BankDetailBeanRemote bankDetailBeanRemote;

    public BankDetailAttributeDecorator(BankDetailBeanRemote bankDetailBeanRemote) {
        super(id);
        this.bankDetailBeanRemote = bankDetailBeanRemote;
    }

    @Override
    public void decorate(HttpServletRequest request, AppContext appContext) {
        request.setAttribute("detail", Optional.ofNullable(bankDetailBeanRemote.getBankDetail(appContext.getUserID())).orElse(new BankDetail()));
    }
}
