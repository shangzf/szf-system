package com.shangzf.common.vo.constant;

/**
 * 常用正则表达式
 */
public interface RegexConstant {

    /**
     * 首尾空格
     */
    String LEADING_AND_TRAILING_SPACES = "(^\\s*)|(\\s*$)";
    /**
     * 整数或者小数
     */
    String INTEGER_OR_DECIMAL = "^[0-9]+\\.{0,1}[0-9]{0,2}$";
    /**
     * 只能输入数字
     */
    String DIGITAL = "^[0-9]*$";
    /**
     * 只能输入n位的数字
     */
    String DIGIT_NUMBER = "^\\d{6}$";
    /**
     * 只能输入至少6位的数字
     */
    String LEAST_DIGITS = "^\\d{6,}$";
    /**
     * 只能输入6~18位的数字
     */
    String DIGITS = "^\\d{6,18}$";
    /**
     * 只能输入零和非零开头的数字
     */
    String STARTING_WITH_ZERO_AND_NON_ZERO = "^(0|[1-9][0-9]*)$";
    /**
     * 只能输入有两位小数的正实数
     */
    String TWO_DECIMAL_PLACES = "^[0-9]+(.[0-9]{2})?$";
    /**
     * 只能输入有1~3位小数的正实数
     */
    String DECIMAL_PLACES = "^[0-9]+(.[0-9]{1,3})?$";
    /**
     * 只能输入非零的正整数
     */
    String NON_ZERO_POSITIVE = "^\\+?[1-9][0-9]*$";
    /**
     * 只能输入非零的负整数
     */
    String NON_ZERO_NEGATIVE = "\\-[1-9][]0-9*$";
    /**
     * 只能输入长度为3的字符
     */
    String CHAR_LENGTH_THREE = "^.{3}$";
    /**
     * 只能输入由26个英文字母组成的字符串
     */
    String STRING_LETTERS = "^[A-Za-z]+$";
    /**
     * 只能输入由26个大写英文字母组成的字符串
     */
    String UPPER_CASE_STRING_LETTERS = "^[A-Z]+$";
    /**
     * 只能输入由26个小写英文字母组成的字符串
     */
    String CASE_STRING_LETTERS = "^[a-z]+$";
    /**
     * 只能输入由数字和26个英文字母组成的字符串
     */
    String NUMBER_STRING_LETTERS = "^[A-Za-z0-9]+$";
    /**
     * 只能输入由数字、26个英文字母或者下划线组成的字符串
     */
    String NUMBER_UNDERSCORE_STRING_LETTERS = "^\\w+$";
    /**
     * 验证用户密码,正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线
     */
    String PASSWORD = "^[a-zA-Z]\\w{5,17}$";
    /**
     * 验证是否含有^%&',;=?$\"等字符
     */
    String SPECIAL_CHARACTERS = "[^%&',;=?$\\x22]+";
    /**
     * 只能输入汉字
     */
    String CHINESE_CHARACTER = "^[\\u4e00-\\u9fa5]{0,}$";
    /**
     * 验证Email地址
     */
    String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 验证InternetURL
     */
    String INTERNET_URL = "^http|^https://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
    /**
     * 验证电话号码
     */
    String PHONE = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";
    /**
     * 验证身份证号
     */
    String ID_NUMBER = "^\\d{15}|\\d{18}$";
    /**
     * 验证一年的12个月,正确格式为："01"～"09"和"1"～"12"。
     */
    String MONTH = "^(0?[1-9]|1[0-2])$";
    /**
     * 验证一个月的31天,正确格式为；"01"～"09"和"1"～"31"。
     */
    String DAY = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
    /**
     * 匹配空行
     */
    String EMPTY_LINE = "\\n[\\s|]*\\r";
    /**
     * 匹配html标签
     */
    String HTML = "<(.*)>(.*)<\\/(.*)>|<(.*)\\/>";


}
