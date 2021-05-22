package com.shangzf.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具
 */
public class RegexUtil {

    public static final String PHONEREGULAR = "(^(0|86|17951)?((13[0-9]|15[012356789]|16[2567]|17[012345678]|18[0-9]|14[01456789]|19[012356789])[0-9]{8})$)";

    private static final Pattern PHONE_REGX_PATTERN = Pattern.compile(PHONEREGULAR, Pattern.DOTALL);

    public static boolean isPhone(String phone) {
        return isMatch(phone, PHONE_REGX_PATTERN);
    }

    public static boolean isMatch(String str, String regxStr) {
        Pattern compile = Pattern.compile(regxStr, Pattern.DOTALL);
        return isMatch(str, compile);
    }

    public static boolean isMatch(String str, Pattern regx) {
        Matcher matcher = regx.matcher(str);
        return matcher.matches();
    }
}
