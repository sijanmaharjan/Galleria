package bean.remote;

import bean.entity.RedeemRequest;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RedemptionBeanRemote {
    List<RedeemRequest> getPendingRequests();
    void response(Integer id, String response);
}
