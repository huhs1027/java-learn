package utils;

import java.util.Collection;

/**
 * @author huhongsen
 * @date 2016/3/7
 * 基础集合工具类
 */
public class CollectionUtils {

    /**
     * 校验集合是否为空
     *
     * @param c
     * @return
     */
    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    /**
     * 校验集合是否非空
     *
     * @param c
     * @return
     */
    public static boolean isNotEmpty(Collection c) {
        return !isEmpty(c);
    }
}
