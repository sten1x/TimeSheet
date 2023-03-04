package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURL().toString();

        if (url.endsWith(".jsp")) {
            String newUrl = url.substring(0, url.length() - 4);
            ((HttpServletResponse) response).sendRedirect(newUrl);
        } else {
            chain.doFilter(request, response);
        }
    }
}