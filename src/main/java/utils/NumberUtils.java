package utils;

/**
 * 数字工具类
 *
 * @author huhongsen
 * @date 2018/12/7
 */
public final class NumberUtils {

    public static boolean checkIsNumber(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Number) {
            return true;
        }
        return false;
    }

}
