package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huhongsen
 * @date 2018/2/7
 * 键值对工具类
 */
public class MapUtils {

    /**
     * 构造hashMap
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * map中的key转换成list
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<K> mapKeyToList(Map<K, V> map) {
        List<K> list = ListUtils.newArrayList();
        if (isEmpty(map)) {
            return list;
        }
        list.addAll(map.keySet());
        return list;
    }

    /**
     * map中的value转换成list
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<V> mapValueToList(Map<K, V> map) {
        List<V> list = ListUtils.newArrayList();
        if (isEmpty(map)) {
            return list;
        }
        list.addAll(map.values());
        return list;
    }


    /**
     * map的空校验
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * map非空校验
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }
}
