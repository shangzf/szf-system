package com.shangzf.util;

import java.util.Collection;

/**
 * 集合操作工具类
 *
 * @author zefeng.shang@changhong.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class CollectionUtils {
    /**
     * 空安全检查指定的集合是否为空。
     * Null返回true。
     *
     * @param coll 要检查的集合，可以为null
     * @return 如果为空或null，则为true
     */
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
