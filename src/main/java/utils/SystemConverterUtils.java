package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 进制转换工具类
 *
 * @author huhongsen
 * @date 2018/12/19
 */
public class SystemConverterUtils {

    /**
     * 已经打乱过的62进制原数组. 打乱需要调用shuffleList();
     */
    private static char[] charArrays = {'7', 'c', 'l', 'H', '6', 'd', 'B', 'T', 'I', 'e', 'n', 'j', 'N', 's', 'a', 'O', 'C', 't', 'r', 'v', 'f', 'x', '4', 'F', 'M', '1', 'z', '3', 'i', 'k', 'Y', 'P', 'G', '5', 'g', 'w', 'R', 'L', 'b', '0', 'J', 'Z', 'K', 'm', 'p', 'D', '9', 'Q', 'X', '8', 'o', 'q', 'W', '2', 'A', 'E', 'S', 'u', 'U', 'y', 'h', 'V'};

    /**
     * 10进制转62进制
     * 除进制取余法,最后反序
     */
    public static String conver10To62(long number) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int remainder = (int) (number % 62);
            sb.append(charArrays[remainder]);
            number = number / 62;
            if (number == 0) {
                break;
            }
        }
        return sb.reverse().toString();
    }

    /**
     * 62进制转10进制
     * 单个值*进制的(位数-1)次方的累加和
     */
    public static long conver62To10(String valueOf62) {
        long sum = 0L;
        char[] valueChars = valueOf62.toCharArray();
        //从前往后遍历
        for (int i = 0; i < valueChars.length; i++) {
            //从后往前取
            int index = findIndex(valueChars[valueChars.length - i - 1]);
            //没找到返回-1
            if (index < 0) {
                return -1;
            }
            sum += index * Math.pow((double) 62, (double) i);
        }
        return sum;
    }

    private static int findIndex(char ch) {
        for (int i = 0; i < charArrays.length; i++) {
            if (ch == charArrays[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = conver10To62(65);
        System.out.println("62进制=" + s);
        long l = conver62To10(s);
        System.out.println("10进制=" + l);
    }

    /**
     * 打乱62进制数组.
     */
    private static void shuffleList() {
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890qwertyuiopasdfghjklzxcvbnm".toCharArray();
        List<Character> strings = new ArrayList<>();
        for (char aChar : chars) {
            strings.add(aChar);
        }
        Collections.shuffle(strings);
        StringBuilder charSb = new StringBuilder();
        charSb.append("{");
        for (Character string : strings) {
            if (charSb.length() > 1) {
                charSb.append(",");
            }
            charSb.append("'").append(string).append("'");
        }
        charSb.append("}");
        System.out.println(charSb.toString());
    }
}
