package bean.remote;

import bean.entity.Notification;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface NotificationBeanRemote {
    List<Notification> getMessages(String UID);
    Long getMessageCount(String UID);
    void removeMessage(String UID, Integer NID);
    void removeAllMessages(String UID);
}
