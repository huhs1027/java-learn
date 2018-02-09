package utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author huhongsen
 * @date 2016/3/7
 * 列表工具类
 */
public class ListUtils {

    /**
     * 构造arraylist对象
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * 构造arrayList对象并初始化长度
     *
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> newArrayListInitSize(int size) {
        return new ArrayList<>(size);
    }

    /**
     * 构造arrayList对象并初始化元素
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> newArrayList(T... t) {
        List<T> list = newArrayList();
        if (null == t) {
            return list;
        }
        list.addAll(Arrays.asList(t));
        return list;
    }

    /**
     * 构造arrayList对象并初始化集合
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> List<T> newArrayList(Collection<T> c) {
        List<T> list = newArrayList();
        if (null == c) {
            return list;
        }
        list.addAll(c);
        return list;
    }

    /**
     * list转成list 可以把list中的元素中的filed取出 封装成list
     *
     * @param tList
     * @param fieldName
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> List<E> listToList(List<T> tList, String fieldName) {
        List<E> list = newArrayList();
        if (StringUtils.isBlank(fieldName) || CollectionUtils.isEmpty(tList)) {
            return list;
        }
        //获取Filed
        try {
            Field declaredField = tList.get(0).getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            for (T t : tList) {
                Object o = declaredField.get(t);
                E e = CastUtils.cast(o);
                if (null != e) {
                    list.add(e);
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    /**
     * list转成map 可以把list中的元素的field作为key, 元素做为value
     *
     * @param vList
     * @param fieldName
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> listToMap(List<V> vList, String fieldName) {
        Map<K, V> map = MapUtils.newHashMap();
        if (StringUtils.isBlank(fieldName) || CollectionUtils.isEmpty(vList)) {
            return map;
        }
        try {
            Field declaredField = vList.get(0).getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            for (V v : vList) {
                K k = CastUtils.cast(declaredField.get(v));
                if (null != k) {
                    map.put(k, v);
                }
            }
        } catch (Exception e) {

        }
        return map;
    }

}
