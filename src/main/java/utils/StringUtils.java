package utils;

import java.util.Formatter;

/**
 * @author huhongsen
 * @date 2016/2/1
 * 字符串工具类
 */
public final class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空或空格
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空和空格
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 移除空格
     *
     * @param str
     * @return
     */
    public static String removeBlank(String str) {
        StringBuilder sb = new StringBuilder(50);
        if (isEmpty(str)) {
            return sb.toString();
        }
        char[] chars = str.toCharArray();
        for (char ac : chars) {
            //判断是否等于空格
            if (ac != 32) {
                sb.append(ac);
            }
        }
        return sb.toString();
    }

    /**
     * 2进制数组转换16进制字符串
     */
    public static String bytesToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
