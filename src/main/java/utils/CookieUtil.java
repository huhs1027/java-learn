package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huhongsen
 * @date 2016/3/7
 * cookie工具类
 */
public final class CookieUtil {

    protected static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 获得request中带的cookies的名字
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }

    /**
     * 初始化cookie信息
     */
    public static void cookieInit(HttpServletResponse response, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            Cookie initCookie = new Cookie(key, value);
            initCookie.setPath("/");
            initCookie.setMaxAge(-1);
            response.addCookie(initCookie);
        }
    }

    /**
     * 清空cookies
     */
    public static void cleanCookie(HttpServletResponse response, String key) {
        if (StringUtils.isNotBlank(key)) {
            Cookie initCookie = new Cookie(key, "");
            initCookie.setPath("/");
            initCookie.setMaxAge(0);
            response.addCookie(initCookie);
        }
    }
}
