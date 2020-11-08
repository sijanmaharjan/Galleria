package bean.remote;

import bean.entity.User;
import bean.view.DetailedUser;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserBeanRemote {
    void register(User user);
    User login(String username, String password);
    DetailedUser getDetails(String UID);
    void updateUserDetail(User user);
    void deleteUser(String UID);
    void createRedeemRequest(String UID);
    User find(String uid);
    boolean checkPassword(String id, String psw);
    List<User> listUsers();
    void blockUser(String uid);
    void allowUser(String uid);
}
