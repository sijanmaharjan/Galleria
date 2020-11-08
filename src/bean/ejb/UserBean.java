package bean.ejb;

import bean.entity.EntityMan;
import bean.entity.RedeemRequest;
import bean.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import bean.remote.UserBeanRemote;
import bean.view.DetailedUser;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
public class UserBean implements UserBeanRemote {

    @Override
    public void register(User user) {
        EntityMan.execTransaction(em->em.persist(user));
    }

    @Override
    public User login(String username, String password) {
        return EntityMan.execute(em->{
            try {
                User user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getSingleResult();
                if (BCrypt.checkpw(password, user.getPassword())) {
                    return user;
                } else {
                    return null;
                }
            }catch (NoResultException e){
                return null;
            }
        });
    }

    @Override
    public DetailedUser getDetails(String UID) {
        return EntityMan.execute(em->em.createNamedQuery("DetailedUser.findByUID", DetailedUser.class).setParameter("UID", UID).getSingleResult());
    }

    @Override
    public void updateUserDetail(User user) {
        EntityMan.execTransaction(ex->ex.merge(user));
    }

    @Override
    public void deleteUser(String UID) {
        EntityMan.execTransaction(ex->{
            ex.createNamedQuery("BankDetail.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("Favourite.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("Notification.removeByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("Rating.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("RedeemRequest.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("SoldImage.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("Wishlist.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("Image.deleteByUID").setParameter("UID", UID).executeUpdate();
            ex.createNamedQuery("User.delete").setParameter("UID", UID).executeUpdate();
        });
    }

    @Override
    public void createRedeemRequest(String UID) {
        EntityMan.execTransaction(em->em.persist(new RedeemRequest(UID)));
    }

    @Override
    public User find(String UID) {
        try {
            return EntityMan.execute(em -> em.createNamedQuery("User.findById", User.class).setParameter("UID", UID).getSingleResult());
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public boolean checkPassword(String UID, String password) {
        User user = find(UID);
        return user != null && BCrypt.checkpw(password, user.getPassword());
    }

    @Override
    public List<User> listUsers() {
        return EntityMan.execute(em -> em.createNamedQuery("User.getAll", User.class).getResultList());
    }

    @Override
    public void blockUser(String UID) {
        EntityMan.execTransaction(em -> em.createNamedQuery("User.block").setParameter("UID", UID).executeUpdate());
    }

    @Override
    public void allowUser(String UID) {
        EntityMan.execTransaction(em -> em.createNamedQuery("User.allow").setParameter("UID", UID).executeUpdate());
    }
}
