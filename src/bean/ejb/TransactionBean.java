package bean.ejb;

import bean.entity.EntityMan;
import bean.remote.TransactionBeanRemote;
import bean.view.TransactionStatement;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TransactionBean implements TransactionBeanRemote {
    @Override
    public List<TransactionStatement> getStatements(String UID) {
        return EntityMan.execute(em->em.createNamedQuery("TransactionStatement.get", TransactionStatement.class).setParameter("UID", UID).getResultList());
    }
}
