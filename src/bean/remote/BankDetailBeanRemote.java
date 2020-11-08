package bean.remote;

import bean.entity.BankDetail;

import javax.ejb.Remote;

@Remote
public interface BankDetailBeanRemote {
    void saveBankDetail(BankDetail bankDetail);
    BankDetail getBankDetail(String UID);
    void updateBankDetail(BankDetail bankDetail);
}
