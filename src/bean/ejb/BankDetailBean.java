package bean.ejb;

import bean.entity.BankDetail;
import bean.entity.EntityMan;
import bean.remote.BankDetailBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class BankDetailBean implements BankDetailBeanRemote {
    @Override
    public void saveBankDetail(BankDetail bankDetail) {
        EntityMan.execTransaction(em->em.persist(bankDetail));
    }

    @Override
    public BankDetail getBankDetail(String UID) {
        try {
            return EntityMan.execute(em -> em.createNamedQuery("BankDetail.get", BankDetail.class).setParameter("UID", UID).getSingleResult());
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void updateBankDetail(BankDetail bankDetail) {
        EntityMan.execTransaction(em->em.merge(bankDetail));
    }
}
