package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电话号码工具类
 * Created by huhongsen on 2018/9/12.
 */
public final class PhoneUtil {

    private PhoneUtil() {
    }

    private static Pattern p = Pattern.compile("^[1][3,4,5,6,8,7,9][0-9]{9}$");

    /**
     * 校验电话号码是否正确
     */
    public static boolean isCorrectPhone(String phone) {
        if (phone != null && !"".equals(phone)) {
            if (phone.length() < 11) {
                return false;
            } else {
                Matcher m = p.matcher(phone);
                return m.matches();
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isCorrectPhone("15278967898"));
        System.out.println(isCorrectPhone("1232"));
    }
}
