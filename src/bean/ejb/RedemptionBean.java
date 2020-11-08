package bean.ejb;

import bean.entity.EntityMan;
import bean.entity.RedeemRequest;
import bean.entity.option.RedemptionStatus;
import bean.remote.RedemptionBeanRemote;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RedemptionBean implements RedemptionBeanRemote {

    @Override
    public List<RedeemRequest> getPendingRequests() {
        return EntityMan.execute(em->em.createNamedQuery("RedeemRequest.listPending", RedeemRequest.class)
                .setParameter("queue", RedemptionStatus.in_queue)
                .setParameter("accepted", RedemptionStatus.accepted)
                .getResultList());
    }

    @Override
    public void response(Integer id, String response) {
        EntityMan.execTransaction(em->em.createNamedQuery("RedeemRequest.response")
                .setParameter("RRID", id)
                .setParameter("status", RedemptionStatus.valueOf(response))
                .executeUpdate());
    }
}
