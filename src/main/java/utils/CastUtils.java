package utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 转换工具类
 *
 * @author huhongsen
 * @date 2017/3/7
 */
@SuppressWarnings("unchecked")
public final class CastUtils {

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

    /**
     * 字符串转int
     */
    public static int stringToInteger(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 字符串转long
     */
    public static long stringToLong(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    /**
     * 字符串转double
     */
    public static double stringToDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return 0D;
        }
    }

    /**
     * 字符串转float
     */
    public static float stringToFloat(String str) {
        try {
            return Float.valueOf(str);
        } catch (NumberFormatException e) {
            return 0F;
        }
    }

    /**
     * map转换为bean
     */
    public static <T> T mapToBean(Map<String, String> map, T bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String value = map.get(name);
            if (StringUtils.isNotBlank(value)) {
                try {
                    field.set(bean, value);
                } catch (IllegalAccessException e) {

                }
            }
        }
        // 返回bean
        return bean;
    }

    /**
     * 转换对象到MAP
     */
    public static <T> Map<String, Object> beanToMap(T bean, Map<String, Object> map) {
        // 异常处理
        if (null == map || null == bean) {
            // 实例化对象
            map = MapUtils.newHashMap();
        }
        // 转换类型
        try {
            // 获取所有field
            List<Field> beanField = BeanUtils.findAllFields(bean);
            //非空判断
            if (ListUtils.isNotEmpty(beanField)) {
                // 循环处理
                for (Field field : beanField) {
                    // 暴力获取
                    field.setAccessible(true);
                    // 取得字段名
                    String key = field.getName();
                    //取得字段值
                    Object value = field.get(bean);
                    //添加到map中
                    map.put(key, value);
                }
            }
        } catch (Exception e) {

        }
        // 返回MAP
        return map;
    }

}
