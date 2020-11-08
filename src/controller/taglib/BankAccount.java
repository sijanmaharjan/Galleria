package controller.taglib;

import bean.entity.BankDetail;
import bean.remote.BankDetailBeanRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class BankAccount extends SimpleTagSupport {

    private String UID;

    public void setUID(String UID) {
        this.UID = UID;
    }

    @EJB
    private BankDetailBeanRemote bankDetailBeanRemote;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        BankDetail detail = bankDetailBeanRemote.getBankDetail(UID);
        out.print(detail.getBankName()+", "+detail.getAccountName()+", "+detail.getAccountNumber());
    }
}
