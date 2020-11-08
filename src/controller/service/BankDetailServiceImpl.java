package controller.service;

import bean.entity.BankDetail;
import bean.entity.User;
import bean.remote.BankDetailBeanRemote;

public class BankDetailServiceImpl implements BankDetailService {

    private final BankDetailBeanRemote bankDetailBeanRemote;

    public BankDetailServiceImpl(BankDetailBeanRemote bankDetailBeanRemote) {
        this.bankDetailBeanRemote = bankDetailBeanRemote;
    }

    @Override
    public void createOrUpdateDetails(User user, String bankName, String accountName, String accountNumber) {
        BankDetail bankDetail = new BankDetail();
        bankDetail.setUser(user);
        bankDetail.setBankName(bankName);
        bankDetail.setAccountName(accountName);
        bankDetail.setAccountNumber(accountNumber);
        BankDetail expectedB = bankDetailBeanRemote.getBankDetail(user.getId());
        if(expectedB == null) {
            bankDetailBeanRemote.saveBankDetail(bankDetail);
        }else{
            bankDetail.setId(expectedB.getId());
            bankDetailBeanRemote.updateBankDetail(bankDetail);
        }
    }
}
