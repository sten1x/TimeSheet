package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Boolean authenticated = (Boolean) ((HttpServletRequest) request).getSession().getAttribute("loggedIn");
        String url = ((HttpServletRequest) request).getRequestURL().toString();

        if (authenticated == null && !url.endsWith("login")) {
            ((HttpServletResponse) response).sendRedirect("login");
        } else {
            chain.doFilter(request, response);
        }


    }
}