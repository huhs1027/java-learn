package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss过滤器
 *
 * @author huhongsen
 * @date 2019/1/22
 */
public class XssFilter implements Filter {
    public XssFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XSSHttpRequestWrapper((HttpServletRequest) request), response);
    }
}
