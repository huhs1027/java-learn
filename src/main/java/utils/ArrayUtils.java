package utils;

/**
 * 数组工具类
 *
 * @author huhongsen
 * @date 2019/1/22
 */
public final class ArrayUtils {

    /**
     * 判断数组为空
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    /**
     * 判断数组非空
     */
    public static boolean isNotEmpty(Object[] obj) {
        return !isEmpty(obj);
    }
}
