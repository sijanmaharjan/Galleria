package controller.servlet.filter;

import app.AppContext;
import bean.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "BlockingFilter", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class BlockingFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if(!uri.contains("galleria") || context.getAttribute(AppContext.USER_ID) == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            AppContext appContext = AppContext.parseContext(context);
            User user = appContext.getUser();
            if(user != null) {  // general user
                if(user.getBlocked() == true){  //user is blocked
                    req.getSession().invalidate();
                    ServletContext context = req.getServletContext();
                    context.setAttribute("error", "Unfortunately You are blocked from accessing this site!");
                    context.setAttribute("status", 403);
                    resp.sendRedirect(appContext.getHomeUri());
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
    }
}
