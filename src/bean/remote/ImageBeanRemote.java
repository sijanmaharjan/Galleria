package bean.remote;

import bean.entity.Image;
import bean.view.DetailedImage;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ImageBeanRemote {

    enum Filter{
        mostly_liked,
        highly_rated,
        price_range
    }
    List<DetailedImage> getDetailedImages(String UID, String CID, String findText, Filter filterBy, Double min, Double max);
    List<DetailedImage> getMyDetailedImages(String UID, String CID, String findText, Filter filterBy, Double min, Double max);
    List<DetailedImage> getPurchasedImages(String UID);
    List<DetailedImage> getFavouriteImages(String UID, String CID, String findText, Filter filterBy, Double min, Double max);
    DetailedImage findImageDetail(String UID, String IID);
    Image find(String IID, String UID);
    List<Image> listImages(String UID);
    List<Image> listByCategoryId(String CID);
    void addNewImage(Image image);
    void updateImageDetail(Image image);
    void markAsLiked(String IID, String UID);
    void markAsDisliked(String IID, String UID);
    void rateImage(String IID, String UID, int points);
    void updateRating(String IID, String UID, int points);
    void unrateImage(String IID, String UID);
    void deleteImage(String IID);
    boolean isOwner(String iid, String uid);
    boolean isAccessible(String iid, String uid);
    boolean checkRated(String iid, String uid);
    void toggleAvailability(String iid, String id, Boolean available);
    Image get(String IID);
    List<Image> getShowcaseImages();
}
