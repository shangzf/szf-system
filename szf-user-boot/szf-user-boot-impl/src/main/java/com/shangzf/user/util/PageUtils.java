package com.shangzf.user.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.util.ConvertUtils;

public class PageUtils {

    public static <S, T> Page<T> page(Page<S> source, Class<T> taeget){
        Page<T> result = new Page<>();
        ConvertUtils.convert(source,result);
        result.setRecords(ConvertUtils.convertList(source.getRecords(),taeget));
        return result;
    }
}
