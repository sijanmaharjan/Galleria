package controller.servlet.filter;

import app.AdminUser;
import app.AppContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class AdminFilter implements Filter {

    private String homeUri = "";
    private String adminHome = "";

    public AdminFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        homeUri = context.getInitParameter(AppContext.HOME_URI);
        adminHome = context.getInitParameter(AppContext.ADMIN_HOME);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        if(!uri.contains(adminHome)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String UID = (String) session.getAttribute(AppContext.USER_ID);
            if (session.isNew() || !AdminUser.UID.equals(UID)) {
                if (!uri.endsWith(adminHome)) {
                    res.sendRedirect(homeUri);
                } else {
                    if(UID != null) { session.invalidate(); }
                    res.setStatus(402);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}

