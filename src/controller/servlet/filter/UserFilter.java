package controller.servlet.filter;

import app.AdminUser;
import app.AppContext;
import bean.remote.UserBeanRemote;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class UserFilter implements Filter {

    private String homeUri = "";
    private String adminHome = "";
    private ServletContext context;

    @EJB
    private UserBeanRemote userBeanRemote;

    public UserFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
        homeUri = context.getInitParameter(AppContext.HOME_URI);
        adminHome = context.getInitParameter(AppContext.ADMIN_HOME);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        if(!uri.contains("galleria")
                || uri.endsWith("galleria.login")
                || uri.endsWith("galleria.logout")
                || uri.endsWith("galleria.signup")
                || uri.contains(adminHome)
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            if (session.isNew() || session.getAttribute(AppContext.USER_ID) == null || AdminUser.UID.equals(session.getAttribute(AppContext.USER_ID))) {
                session.invalidate();
                if (!uri.endsWith(homeUri)) {
                    res.sendRedirect(homeUri);
                } else {
                    if(context.getAttribute("error") != null){
                        res.setStatus((int)context.getAttribute("status"));
                    }else{
                        res.setStatus(402);
                    }
                    context.setAttribute(AppContext.USER_LOGGED_IN, false);
                    context.setAttribute(AppContext.USER_ID, null);
                    context.setAttribute(AppContext.LOGGED_IN_USER, null);
                    context.setAttribute(AppContext.LOGGED_IN_USER_DETAIL, null);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                String UID = (String) session.getAttribute(AppContext.USER_ID);
                context.setAttribute(AppContext.USER_LOGGED_IN, true);
                context.setAttribute(AppContext.USER_ID, UID);
                context.setAttribute(AppContext.LOGGED_IN_USER, userBeanRemote.find(UID));
                context.setAttribute(AppContext.LOGGED_IN_USER_DETAIL, userBeanRemote.getDetails(UID));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}
