package bean.ejb;

import bean.entity.EntityMan;
import bean.entity.Notification;
import bean.remote.NotificationBeanRemote;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class NotificationBean implements NotificationBeanRemote {
    @Override
    public List<Notification> getMessages(String UID) {
        return EntityMan.execute(em->em.createNamedQuery("Notification.messages", Notification.class).setParameter("UID", UID).getResultList());
    }

    @Override
    public Long getMessageCount(String UID) {
        return EntityMan.execute(em->em.createNamedQuery("Notification.message_count", Long.class).setParameter("UID", UID).getSingleResult());
    }

    @Override
    public void removeMessage(String UID, Integer NID) {
        EntityMan.execTransaction(em->em.createNamedQuery("Notification.removeById").setParameter("UID", UID).setParameter("NID", NID).executeUpdate());
    }

    @Override
    public void removeAllMessages(String UID) {
        EntityMan.execTransaction(em->em.createNamedQuery("Notification.removeByUID").setParameter("UID", UID).executeUpdate());
    }
}
