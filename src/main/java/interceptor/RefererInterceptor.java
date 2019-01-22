package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import utils.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 引用拦截器，防止资源被盗用（主要是图片）
 */
public class RefererInterceptor extends HandlerInterceptorAdapter {

    private String defaultReferer;

    private static String[] AUTH_REFERER_ARRAY;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        String referer = httpServletRequest.getHeader("referer");
        if (StringUtils.isBlank(referer)) {
            return true;
        }

        for (String authReferer : AUTH_REFERER_ARRAY) {
            if (referer.contains(authReferer)) {
                return true;
            }
        }

        return false;
    }

    @PostConstruct
    public void init() {
        AUTH_REFERER_ARRAY = defaultReferer.split(",");
    }
}
