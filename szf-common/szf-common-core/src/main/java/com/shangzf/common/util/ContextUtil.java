package com.shangzf.common.util;

import com.shangzf.common.context.BaseApplicationContext;
import com.shangzf.common.constant.StringConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class ContextUtil extends BaseApplicationContext {

    public static String getMessage(String key, Object... args) {
        return getMessage(key, args, getLocale());
    }

    public static String getMessage(String key, Object[] args, Locale locale) {
        if (StringUtils.isBlank(key)) {
            return StringConstant.EMPTY;
        }
        return getApplicationContext().getMessage(key, args, locale);
    }

    public static String getMessage(String key, Object[] args, String defaultMessage, Locale locale) {
        if (StringUtils.isBlank(key)) {
            return StringConstant.EMPTY;
        }
        return getApplicationContext().getMessage(key, args, defaultMessage, locale);
    }

    public static Locale getLocale() {
        return Locale.getDefault();
    }

}
