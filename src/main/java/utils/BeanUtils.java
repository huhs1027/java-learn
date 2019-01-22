package utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * javaBean工具类
 *
 * @author huhongsen
 * @date 2018/11/1
 */
public final class BeanUtils {

    /**
     * 获取类对象名
     */
    public static String getBeanName(Class clazz) {
        String simpleName = clazz.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    /**
     * 取得对象的所有字段列表
     */
    public static <T> List<Field> findAllFields(T bean) {
        // 所有字段列表
        List<Field> fields = ListUtils.newArrayList();
        // 取得对应类型
        Class tmpClass = bean.getClass();
        // 循环处理
        while (tmpClass != null && !tmpClass.getName().toLowerCase().equals("java.lang.object")) {
            // 当父类为null的时候说明到达了最上层的父类(Object类).
            fields.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
            // 得到父类,然后赋给自己
            tmpClass = tmpClass.getSuperclass();
        }
        // 返回字段列表
        return fields;
    }

    /**
     * 取得对应值
     *
     * @param bean
     * @param field
     * @return
     */
    public static Object findFiledValue(Object bean, Field field) {
        // 定义对象的值
        Object value = null;
        try {
            // 取值
            value = field.get(bean);
        } catch (IllegalAccessException e) {

        }
        // 返回值
        return value;
    }

}
