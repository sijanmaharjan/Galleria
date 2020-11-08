package bean.remote;

import bean.view.TransactionStatement;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TransactionBeanRemote {
    List<TransactionStatement> getStatements(String UID);
}
