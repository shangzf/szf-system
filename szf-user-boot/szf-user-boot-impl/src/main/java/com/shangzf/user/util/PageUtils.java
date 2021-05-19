package com.shangzf.user.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.common.util.ConvertUtil;

public class PageUtils {

    public static <S, T> Page<T> page(Page<S> source, Class<T> taeget){
        Page<T> result = new Page<>();
        ConvertUtil.convert(source,result);
        result.setRecords(ConvertUtil.convertList(source.getRecords(),taeget));
        return result;
    }
}
