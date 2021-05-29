package com.shangzf.common.util;

import com.shangzf.common.constant.StringConstant;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 属性拷贝工具类
 */
public class ConvertUtil {

    private static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    public static <S, T> T convert(S source, T target) {
        if (Objects.isNull(source) || Objects.isNull(target)) {
            return null;
        }
        BeanCopier copier = getBeanCopier(source, target);
        copier.copy(source, target, null);
        return target;
    }

    public static <S, T> T convert(S source, Class<T> target) {
        try {
            return convert(source, target.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <S, T> List<T> convertList(List<S> source, Class<T> target) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        return source.stream().map(s -> convert(s, target)).collect(Collectors.toList());
    }

    private static <S, T> String genKey(S source, T target) {
        return source.getClass().getName() + StringConstant.UNDER_LINE + target.getClass().getName();
    }

    private static <S, T> BeanCopier getBeanCopier(S source, T target) {
        String key = genKey(source, target);
        BeanCopier copier = BEAN_COPIER_CACHE.get(key);
        if (Objects.isNull(copier)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_CACHE.put(key, copier);
        }
        return copier;
    }
}

