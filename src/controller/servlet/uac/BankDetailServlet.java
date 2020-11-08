package controller.servlet.uac;

import app.AppContext;
import bean.entity.BankDetail;
import bean.remote.BankDetailBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/galleria.update(user.bank.detail)")
public class BankDetailServlet extends HttpServlet {
    @EJB
    private BankDetailBeanRemote bankDetailBeanRemote;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String bankName = req.getParameter("bankName");
        String accountName = req.getParameter("accountName");
        String accountNumber = req.getParameter("accountNumber");
        BankDetail bankDetail = bankDetailBeanRemote.getBankDetail(appContext.getUserID());
        if(bankDetail == null){
            bankDetail = new BankDetail();
            bankDetail.setUser(appContext.getUser());
            bankDetail.setBankName(bankName);
            bankDetail.setAccountName(accountName);
            bankDetail.setAccountNumber(accountNumber);
            bankDetailBeanRemote.saveBankDetail(bankDetail);
        }else{
            bankDetail.setBankName(bankName != null ? bankName : bankDetail.getBankName());
            bankDetail.setAccountName(accountName != null ? accountName : bankDetail.getAccountName());
            bankDetail.setAccountNumber(accountNumber != null ? accountNumber : bankDetail.getAccountNumber());
            bankDetailBeanRemote.updateBankDetail(bankDetail);
        }
    }
}
