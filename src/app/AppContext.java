package app;

import bean.entity.User;
import bean.view.DetailedUser;

import javax.servlet.ServletContext;

public class AppContext {

    final public static String USER_ID = "UID";
    final public static String APP_NAME = "APP_NAME";
    final public static String JSP_DIR_PREFIX = "JSP_DIR_PREFIX";
    final public static String HOME_URI = "HOME_URI";
    final public static String ADMIN_HOME = "ADMIN_HOME";
    final public static String USER_LOGGED_IN = "_user_logged_id";
    final public static String LOGGED_IN_USER = "_logged_id_user";
    final public static String LOGGED_IN_USER_DETAIL = "_logged_id_user_detail";

    private final String appName;
    private final boolean userLoggedIn;
    private final String userID;
    private final User user;
    private final DetailedUser detailedUser;
    private final String dirPrefix;
    private final String homeUri;
    private final String adminHome;

    public AppContext(String appName,
                      boolean userLoggedIn,
                      String userID,
                      User user,
                      DetailedUser detailedUser,
                      String dirPrefix,
                      String homeUri,
                      String adminHome) {
        this.appName = appName;
        this.userLoggedIn = userLoggedIn;
        this.userID = userID;
        this.user = user;
        this.detailedUser = detailedUser;
        this.dirPrefix = dirPrefix;
        this.homeUri = homeUri;
        this.adminHome = adminHome;
    }

    public String getAppName() {
        return appName;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public String getUserID() {
        return userID;
    }

    public User getUser() {
        return user;
    }

    public DetailedUser getDetailedUser() {
        return detailedUser;
    }

    public String getDirPrefix() {
        return dirPrefix;
    }

    public String getHomeUri() {
        return homeUri;
    }

    public String getAdminHome() {
        return adminHome;
    }

    public static AppContext parseContext(ServletContext context){
        return new AppContext(
                context.getInitParameter(APP_NAME),
                (context.getAttribute(USER_LOGGED_IN) != null
                        && (boolean) context.getAttribute(USER_LOGGED_IN)),
                (String) context.getAttribute(USER_ID),
                (User) context.getAttribute(LOGGED_IN_USER),
                (DetailedUser) context.getAttribute(LOGGED_IN_USER_DETAIL),
                context.getInitParameter(JSP_DIR_PREFIX),
                context.getInitParameter(HOME_URI),
                context.getInitParameter(ADMIN_HOME)
        );
    }
}
