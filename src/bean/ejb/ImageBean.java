package bean.ejb;

import bean.entity.EntityMan;
import bean.entity.Favourite;
import bean.entity.Image;
import bean.entity.Rating;
import bean.remote.ImageBeanRemote;
import bean.view.DetailedImage;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
public class ImageBean implements ImageBeanRemote {
    @Override
    public List<DetailedImage> getDetailedImages(String UID, String CID, String findText, Filter filterBy, Double min, Double max) {
        final String sql =
                "SELECT i.* "+
                "FROM images i " +
                ((CID != null)?", Image m ":"")+
                ((filterBy != null && filterBy == Filter.mostly_liked)?", mostly_liked_images x ":"")+
                ((filterBy != null && filterBy == Filter.highly_rated)?", highly_rated_images x ":"")+
                "WHERE i.UID = '"+UID+"' "+
                ((filterBy != null && (filterBy == Filter.mostly_liked || filterBy == Filter.highly_rated))?"AND i.IID = x.IID ":"")+
                ((filterBy != null && filterBy == Filter.price_range)?"AND i.price >= "+min+" AND i.price <= "+max+" " : "")+
                ((CID != null)?"AND i.IID = m.IID AND m.CID = '"+CID+"' ":"")+
                ((findText != null)?"AND LOWER(i.title) LIKE '%"+findText.toLowerCase()+"%' ":"")+
                ((filterBy == null)?"ORDER BY RAND()":((filterBy == Filter.mostly_liked)?"ORDER BY x.likes DESC":((filterBy == Filter.highly_rated)?"ORDER BY x.rates DESC":((filterBy == Filter.price_range)?"ORDER BY i.price ASC":"ORDER BY RAND()"))));
        return EntityMan.execute(em->em.createNativeQuery(sql, DetailedImage.class).getResultList());
    }

    @Override
    public List<DetailedImage> getMyDetailedImages(String UID, String CID, String findText, Filter filterBy, Double min, Double max) {
        final String sql =
                "SELECT i.* "+
                        "FROM images i " +
                        ((CID != null)?", Image m ":"")+
                        ((filterBy != null && filterBy == Filter.mostly_liked)?", mostly_liked_images x ":"")+
                        ((filterBy != null && filterBy == Filter.highly_rated)?", highly_rated_images x ":"")+
                        "WHERE i.UID = '"+UID+"' "+
                        "AND i.uploaded_by='"+UID+"' "+
                        ((filterBy != null && (filterBy == Filter.mostly_liked || filterBy == Filter.highly_rated))?"AND i.IID = x.IID ":"")+
                        ((filterBy != null && filterBy == Filter.price_range)?"AND i.price >= "+min+" AND i.price <= "+max+" " : "")+
                        ((CID != null)?"AND i.IID = m.IID AND m.CID = '"+CID+"' ":"")+
                        ((findText != null)?"AND LOWER(i.title) LIKE '%"+findText.toLowerCase()+"%' ":"")+
                        ((filterBy == null)?"ORDER BY RAND()":((filterBy == Filter.mostly_liked)?"ORDER BY x.likes DESC":((filterBy == Filter.highly_rated)?"ORDER BY x.rates DESC":((filterBy == Filter.price_range)?"ORDER BY i.price ASC":"ORDER BY RAND()"))));
        return EntityMan.execute(em->em.createNativeQuery(sql, DetailedImage.class).getResultList());
    }

    @Override
    public List<DetailedImage> getPurchasedImages(String UID) {
        final String sql =
                "SELECT i.* "+
                        "FROM images i, Sold_Image s " +
                        "WHERE s.IID = i.IID " +
                        "AND s.UID = '"+UID+"' "+
                        "AND i.UID = '"+UID+"' "+
                        "ORDER BY s.timestamp DESC ";
        return EntityMan.execute(em->em.createNativeQuery(sql, DetailedImage.class).getResultList());
    }

    @Override
    public List<DetailedImage> getFavouriteImages(String UID, String CID, String findText, Filter filterBy, Double min, Double max) {
        final String sql =
                "SELECT i.* "+
                        "FROM images i " +
                        ((CID != null)?", Image m ":"")+
                        ((filterBy != null && filterBy == Filter.mostly_liked)?", mostly_liked_images x ":"")+
                        ((filterBy != null && filterBy == Filter.highly_rated)?", highly_rated_images x ":"")+
                        "WHERE i.UID = '"+UID+"' "+
                        "AND i.is_favourite = 1 "+
                        ((filterBy != null && (filterBy == Filter.mostly_liked || filterBy == Filter.highly_rated))?"AND i.IID = x.IID ":"")+
                        ((filterBy != null && filterBy == Filter.price_range)?"AND i.price >= "+min+" AND i.price <= "+max+" " : "")+
                        ((CID != null)?"AND i.IID = m.IID AND m.CID = '"+CID+"' ":"")+
                        ((findText != null)?"AND LOWER(i.title) LIKE '%"+findText.toLowerCase()+"%' ":"")+
                        ((filterBy == null)?"ORDER BY RAND()":((filterBy == Filter.mostly_liked)?"ORDER BY x.likes DESC":((filterBy == Filter.highly_rated)?"ORDER BY x.rates DESC":((filterBy == Filter.price_range)?"ORDER BY i.price ASC":"ORDER BY RAND()"))));
        return EntityMan.execute(em->em.createNativeQuery(sql, DetailedImage.class).getResultList());
    }

    @Override
    public DetailedImage findImageDetail(String UID, String IID) {
        return EntityMan.execute(em->
                em.createNamedQuery("DetailedImage.find", DetailedImage.class)
                        .setParameter("UID", UID)
                        .setParameter("IID", IID)
                        .getSingleResult()
        );
    }

    @Override
    public Image find(String IID, String UID) {
        try {
            return EntityMan.execute(em ->
                    em.createNamedQuery("Image.findById", Image.class)
                            .setParameter("IID", IID)
                            .setParameter("UID", UID)
                            .getSingleResult()
            );
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Image> listImages(String UID) {
        return EntityMan.execute(em->
                em.createNamedQuery("Image.listByUID", Image.class)
                        .setParameter("UID", UID)
                        .getResultList()
        );
    }

    @Override
    public List<Image> listByCategoryId(String CID) {
        return EntityMan.execute(em->
                em.createNamedQuery("Image.listByCID", Image.class)
                        .setParameter("CID", CID)
                        .getResultList()
        );
    }

    @Override
    public void addNewImage(Image image) {
        EntityMan.execTransaction(em->em.persist(image));
    }

    @Override
    public void updateImageDetail(Image image) {
        EntityMan.execTransaction(em->em.merge(image));
    }

    @Override
    public void markAsLiked(String IID, String UID) {
        EntityMan.execTransaction(em->em.persist(new Favourite(IID, UID)));
    }

    @Override
    public void markAsDisliked(String IID, String UID) {
        EntityMan.execTransaction(em->em.createNamedQuery("Favourite.deleteBy_IID_UID").setParameter("IID", IID).setParameter("UID", UID).executeUpdate());
    }

    @Override
    public void rateImage(String IID, String UID, int point) {
        EntityMan.execTransaction(em->em.persist(new Rating(IID, UID, point)));
    }

    @Override
    public void updateRating(String IID, String UID, int point) {
        EntityMan.execTransaction(em->em.createNativeQuery("UPDATE Rating r SET r.point = "+point+" WHERE r.IID='"+IID+"' AND r.UID='"+UID+"'").executeUpdate());
    }

    @Override
    public void unrateImage(String IID, String UID) {
        EntityMan.execTransaction(em->em.createNamedQuery("Rating.unrate").setParameter("IID", IID).setParameter("UID", UID).executeUpdate());
    }

    @Override
    public boolean checkRated(String IID, String UID) {
        return EntityMan.execute(em->em.createNamedQuery("Rating.checkRated", Long.class).setParameter("IID", IID).setParameter("UID", UID).getSingleResult() > 0);
    }

    @Override
    public void toggleAvailability(String IID, String UID, Boolean available) {
        EntityMan.execTransaction(em -> {
            em.createNamedQuery("Image.toggle_availability")
                    .setParameter("available", available)
                    .setParameter("IID", IID)
                    .setParameter("UID", UID)
                    .executeUpdate();
        });
    }

    @Override
    public Image get(String IID) {
        return EntityMan.execute(em->em.find(Image.class, IID));
    }

    @Override
    public List<Image> getShowcaseImages() {
        return EntityMan.execute(em->em.createNativeQuery("SELECT i.* FROM Image i, User u WHERE i.UID = u.UID AND u.blocked = false AND i.available=true AND i.price=0.0 ORDER BY RAND() LIMIT 3", Image.class).getResultList());
    }

    @Override
    public void deleteImage(String IID) {
        EntityMan.execTransaction(ex->{
            ex.createNamedQuery("Favourite.deleteByIID").setParameter("IID", IID).executeUpdate();
            ex.createNamedQuery("Notification.removeByIID").setParameter("IID", IID).executeUpdate();
            ex.createNamedQuery("Rating.deleteByIID").setParameter("IID", IID).executeUpdate();
            ex.createNamedQuery("SoldImage.deleteByIID").setParameter("IID", IID).executeUpdate();
            ex.createNamedQuery("Wishlist.deleteByIID").setParameter("IID", IID).executeUpdate();
            ex.createNamedQuery("Image.deleteByID").setParameter("IID", IID).executeUpdate();
        });
    }

    @Override
    public boolean isOwner(String IID, String UID) {
        return EntityMan.execute(em-> {
            Long count = (em.createNamedQuery("Image.checkOwner", Long.class)
                    .setParameter("IID", IID)
                    .setParameter("UID", UID)
                    .getSingleResult());
            return count != null && count > 0;
        });
    }


    @Override
    public boolean isAccessible(String IID, String UID) {
        return EntityMan.execute(em->{
            Long countFree = (em.createNamedQuery("Image.checkFree", Long.class)
                    .setParameter("IID", IID)
                    .getSingleResult());
            Long countPurchased = (em.createNamedQuery("Image.checkPurchased", Long.class)
                    .setParameter("IID", IID)
                    .setParameter("UID", UID)
                    .getSingleResult());
            boolean accept = ((countFree != null && countFree > 0) || (countPurchased != null && countPurchased > 0));
            Long count = (em.createNamedQuery("Image.checkAccessible", Long.class)
                    .setParameter("IID", IID)
                    .setParameter("UID", UID)
                    .setParameter("accept", accept)
                    .getSingleResult());
            return count != null && count > 0;
        });
    }
}
