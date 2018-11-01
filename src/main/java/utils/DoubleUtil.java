package utils;

import java.math.BigDecimal;

/**
 * Created by guozhenbin on 17/5/15.
 */
public class DoubleUtil {

    private static final int DEF_DIV_SCALE = 10;

    /**
     * 获取小数位数
     *
     * @param balance
     * @return
     */
    public static int getNumberDecimalNumber(Double balance, boolean abs) {
        String balanceStr = new BigDecimal(balance).toString();
        return getNumberDecimalNumber(balanceStr, abs);
    }

    /**
     * 获取小数位数
     *
     * @param balance
     * @return
     */
    public static int getNumberDecimalNumber(String balance, boolean abs) {
        int dcimalDigits = 0;
        if (StringUtils.isBlank(balance)) {
            return dcimalDigits;
        }
        BigDecimal b = new BigDecimal(balance);
        if (abs) {
            b = b.abs();
        }
        balance = b.toString();
        int indexOf = balance.indexOf(".");
        if (indexOf > 0) {
            dcimalDigits = indexOf;
        }
        return dcimalDigits;
    }

    /**
     * 获取小数位数
     *
     * @param balance
     * @return
     */
    public static int getNumberDecimalDigits(Double balance) {
        String balanceStr = new BigDecimal(balance).toString();
        return getNumberDecimalDigits(balanceStr);
    }

    /**
     * 获取小数位数
     *
     * @param balance
     * @return
     */
    public static int getNumberDecimalDigits(String balance) {
        int dcimalDigits = 0;
        if (StringUtils.isBlank(balance)) {
            return dcimalDigits;
        }
        int indexOf = balance.indexOf(".");
        if (indexOf > 0) {
            dcimalDigits = balance.length() - 1 - indexOf;
        }
        return dcimalDigits;
    }

    /**
     * 两个Double数相加
     *
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double add(Double v1, Double v2) {
        if (v1 == null && v2 == null) {
            return null;
        }
        if (v1 != null && v2 == null) {
            return v1;
        }
        if (v1 == null && v2 != null) {
            return v2;
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.add(b2).doubleValue();
    }

    /**
     * 两个Double数相减
     *
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double sub(Double v1, Double v2) {
        if (v1 == null && v2 == null) {
            return null;
        }
        if (v1 != null && v2 == null) {
            return v1;
        }
        if (v1 == null && v2 != null) {
            return v2;
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 两个Double数相乘
     *
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double mul(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 两个Double数相除
     *
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double div(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 两个Double数相除，并保留scale位小数
     *
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(Double v1, Double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
