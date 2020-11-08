package bean.ejb;

import bean.entity.*;
import bean.remote.WishBeanRemote;
import bean.view.Wish;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class WishBean implements WishBeanRemote {
    @Override
    public List<Wish> getWishlist(String UID) {
        return EntityMan.execute(em->em.createNamedQuery("Wish.list", Wish.class).setParameter("UID", UID).getResultList());
    }

    @Override
    public void addImageToWishlist(String IID, String UID) {
        EntityMan.execTransaction(em->em.persist(new Wishlist(IID, UID)));
    }

    @Override
    public void removeImageFromWishlist(String IID, String UID) {
        EntityMan.execTransaction(em->em.createNamedQuery("Wishlist.deleteBy_IID_UID").setParameter("IID", IID).setParameter("UID", UID).executeUpdate());
    }

    @Override
    public void checkoutWishlist(String UID) {
        EntityMan.execTransaction(em->{
            List<Wishlist> wishlists = em.createNamedQuery("Wishlist.getByUID", Wishlist.class).setParameter("UID", UID).getResultList();
            wishlists.forEach(w->{
                em.persist(new SoldImage(w.getImage().getId(), UID, new Date()));
            });
            em.createNamedQuery("Wishlist.deleteByUID").setParameter("UID", UID).executeUpdate();
        });
    }

    @Override
    public boolean availableInWishlist(String IID, String UID) {
        return EntityMan.execute(em->em.createNamedQuery("Wishlist.checkAvailable", Long.class).setParameter("IID", IID).setParameter("UID", UID).getSingleResult()>0);
    }
}
