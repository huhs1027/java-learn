package utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author huhongsen
 * @date 2017/2/7
 * 哈希工具类
 */
public class SetUtils {

    /**
     * 构造hashSet并返回
     *
     * @param <T>
     * @return
     */
    public static <T> Set<T> newHashSet() {
        return new HashSet<>();
    }

    /**
     * 构造hashSet并初始化元素
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> Set<T> newHashSet(Collection<T> c) {
        Set<T> set = newHashSet();
        if (CollectionUtils.isEmpty(c)) {
            return set;
        }
        set.addAll(c);
        return set;
    }

    /**
     * 取差集 set1有 set2没有的
     *
     * @param <T>
     * @return
     */
    public static <T> Set<T> difference(Set<T> set1, Set<T> set2) {
        Set<T> set = newHashSet();
        if (CollectionUtils.isEmpty(set1)) {
            return set;
        }
        if (CollectionUtils.isEmpty(set2)) {
            return set1;
        }
        //移除所有重复的
        set1.removeAll(set2);
        return set1;
    }

    /**
     * 取交集
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> set = newHashSet();
        if (CollectionUtils.isEmpty(set1) || CollectionUtils.isEmpty(set2)) {
            return set;
        }
        set1.retainAll(set2);
        set = set1;
        return set;
    }

    /**
     * 取并集
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> set = newHashSet();
        if (CollectionUtils.isNotEmpty(set1)) {
            set.addAll(set1);
        }
        if (CollectionUtils.isNotEmpty(set2)) {
            set.addAll(set2);
        }
        return set;
    }
}
