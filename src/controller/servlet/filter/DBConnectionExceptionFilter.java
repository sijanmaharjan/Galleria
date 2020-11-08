package controller.servlet.filter;

import javax.ejb.EJBException;
import javax.persistence.PersistenceException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ExceptionFilter", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class DBConnectionExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (EJBException | PersistenceException e){
            String cause = (e.getCause().getMessage());
            if (cause.contains("java.sql.SQLException: No suitable driver found")) {
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                resp.sendError(512, "Database Connection Failed");
            } else throw e;
        }
    }
}
