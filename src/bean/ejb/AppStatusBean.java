package bean.ejb;

import bean.entity.EntityMan;
import bean.remote.AppStatusBeanRemote;
import bean.view.*;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AppStatusBean implements AppStatusBeanRemote {
    @Override
    public AppStatus getAppStatus() {
        return EntityMan.execute(em->em.createNamedQuery("AppStatus.get", AppStatus.class).getSingleResult());
    }

    @Override
    public List<MostlyLikedImage> getMostlyLikedImages(String UID) {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                "FROM mostly_liked_images m, Image i " +
                "WHERE i.IID = m.IID " +
                "AND i.UID = '"+UID+"' " +
                "ORDER BY m.likes DESC " +
                "LIMIT 10"
                , MostlyLikedImage.class).getResultList());
    }

    @Override
    public List<HighlyRatedImage> getHighlyRatedImages(String UID) {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                "FROM highly_rated_images m, Image i " +
                "WHERE m.IID = i.IID " +
                "AND i.UID = '"+UID+"' " +
                "ORDER BY m.rates DESC, m.rate_count DESC " +
                "LIMIT 10"
                , HighlyRatedImage.class).getResultList());
    }

    @Override
    public List<MostlySoldImage> getMostlySoldImages(String UID) {
        return EntityMan.execute(em->em.createNativeQuery(
             "SELECT m.* " +
                "FROM mostly_sold_images m, Image i " +
                "WHERE i.IID = m.IID " +
                "AND i.UID = '"+UID+"' " +
                "ORDER BY m.sales DESC " +
                "LIMIT 10"
                , MostlySoldImage.class).getResultList());
    }

    @Override
    public List<MostlyLikedImage> getMostlyLikedImages() {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                "FROM mostly_liked_images m " +
                "LIMIT 10"
                , MostlyLikedImage.class).getResultList());
    }

    @Override
    public List<HighlyRatedImage> getHighlyRatedImages() {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                        "FROM highly_rated_images m " +
                        "LIMIT 10"
                , HighlyRatedImage.class).getResultList());
    }

    @Override
    public List<MostlySoldImage> getMostlySoldImages() {
        return EntityMan.execute(em->em.createNativeQuery(
        "SELECT m.* " +
                "FROM mostly_sold_images m " +
                "LIMIT 10"
                , MostlySoldImage.class).getResultList());
    }

    @Override
    public List<MostExpensiveImage> getMostExpensiveImages() {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                        "FROM most_expensive_images m " +
                        "LIMIT 10"
                , MostExpensiveImage.class).getResultList());
    }

    @Override
    public List<TopEarningAccount> getTopEarningAccounts() {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                        "FROM top_earning_accounts m " +
                        "LIMIT 10"
                , TopEarningAccount.class).getResultList());
    }

    @Override
    public List<TopRatedAccount> getTopRatedAccounts() {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT m.* " +
                        "FROM top_rated_accounts m " +
                        "LIMIT 10"
                , TopRatedAccount.class).getResultList());
    }
}
