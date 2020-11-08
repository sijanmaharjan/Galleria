package bean.remote;

import bean.view.Wish;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface WishBeanRemote {
    List<Wish> getWishlist(String UID);
    void addImageToWishlist(String IID, String UID);
    void removeImageFromWishlist(String IID, String UID);
    void checkoutWishlist(String UID);
    boolean availableInWishlist(String iid, String uid);
}
