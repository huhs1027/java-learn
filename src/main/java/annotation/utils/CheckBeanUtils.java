package annotation.utils;

import annotation.FieldNotNull;
import annotation.FieldNumberScope;
import utils.ListUtils;
import utils.NumberUtils;
import utils.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 参数检查工具类
 *
 * @author huhongsen
 * @date 2018/12/7
 */
public class CheckBeanUtils {

    public static void checkBean(Object bean) throws IllegalAccessException {
        if (bean == null) {
            throw new NullPointerException("对象不能为空!");
        }
        //获取所有字段
        List<Field> fieldList = findAllField(bean);
        //处理单个字段的注解
        for (Field field : fieldList) {
            checkField(field, bean);
        }
    }

    public static void checkListBean(List<Object> beanList) throws IllegalAccessException {
        for (Object bean : beanList) {
            checkBean(bean);
        }
    }

    /**
     * 获取所有字段,包含父类
     */
    private static List<Field> findAllField(Object bean) {
        List<Field> fieldList = ListUtils.newArrayListInitSize(16);
        // 取得对应类型
        Class tmpClass = bean.getClass();
        // 循环处理
        while (tmpClass != null && !tmpClass.getName().toLowerCase().equals("java.lang.object")) {
            //当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
            //得到父类,然后赋给自己
            tmpClass = tmpClass.getSuperclass();
        }
        return fieldList;
    }

    private static void checkField(Field field, Object bean) throws IllegalAccessException {
        field.setAccessible(true);
        //非空校验
        checkNotNull(field, bean);
        //范围校验
        checkNumberScope(field, bean);
        //其他需要校验的 可以自行定义注解及方法
    }

    /**
     * 校验数字范围
     */
    private static void checkNumberScope(Field field, Object bean) throws IllegalAccessException {
        if (!field.isAnnotationPresent(FieldNumberScope.class)) {
            return;
        }
        Object value = field.get(bean);
        boolean isNumber = NumberUtils.checkIsNumber(value);
        if (isNumber) {
            FieldNumberScope annotation = field.getAnnotation(FieldNumberScope.class);
            int from = annotation.from();
            int to = annotation.to();
            Number vv = (Number) value;
            if (vv.doubleValue() < from || vv.doubleValue() > to) {
                tips(field, annotation.name(), annotation.message(), "必须在范围" + from + "-" + to + "内");
            }
        }
    }

    /**
     * 校验非空
     */
    private static void checkNotNull(Field field, Object bean) throws IllegalAccessException {
        if (!field.isAnnotationPresent(FieldNotNull.class)) {
            return;
        }
        Object value = field.get(bean);
        if (value == null) {
            //获取注解
            FieldNotNull notNull = field.getAnnotation(FieldNotNull.class);
            //抛出异常
            tips(field, notNull.name(), notNull.message(), "不能为空!");
        }
    }

    /**
     * 抛出错误提示.
     */
    private static void tips(Field field, String name, String message, String tips) {
        String fieldName = name;
        if (StringUtils.isEmpty(name)) {
            fieldName = field.getName();
        }
        if (StringUtils.isEmpty(message)) {
            //抛出异常
            throw new IllegalArgumentException("字段" + fieldName + tips);
        }
        //抛出异常
        throw new IllegalArgumentException("字段" + fieldName + message);
    }
}
