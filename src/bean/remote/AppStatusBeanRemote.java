package bean.remote;

import bean.view.*;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AppStatusBeanRemote {
    AppStatus getAppStatus();
    List<MostlyLikedImage> getMostlyLikedImages(String UID);
    List<HighlyRatedImage> getHighlyRatedImages(String UID);
    List<MostlySoldImage> getMostlySoldImages(String UID);
    List<MostlyLikedImage> getMostlyLikedImages();
    List<HighlyRatedImage> getHighlyRatedImages();
    List<MostlySoldImage> getMostlySoldImages();
    List<MostExpensiveImage> getMostExpensiveImages();
    List<TopEarningAccount> getTopEarningAccounts();
    List<TopRatedAccount> getTopRatedAccounts();
}
