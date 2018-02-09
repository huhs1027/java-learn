package utils;

/**
 * @author huhongsen
 * @date 2017/3/7
 * 转换工具类
 */
@SuppressWarnings("unchecked")
public class CastUtils {

    /**
     * 强转
     *
     * @param o
     * @param <T>
     * @return
     */
    public static <T> T cast(Object o) {
        T t = null;
        try {
            t = (T) o;
        } catch (Exception e) {
            return t;
        }
        return t;
    }

    /**
     * 数字转string
     *
     * @param num
     * @return
     */
    public static String numberToString(Number num) {
        if (null == num) {
            return "";
        }
        return String.valueOf(num);
    }
}
