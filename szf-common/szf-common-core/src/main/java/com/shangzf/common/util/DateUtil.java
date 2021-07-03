package com.shangzf.common.util;

import com.shangzf.common.constant.DateFormatterConstant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String toString(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatterConstant.DATE_TIME_FORMATTER_DEFAULT);
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(formatter);
    }

    public static String toString(Date date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatterConstant.DATE_TIME_FORMATTER_DEFAULT);
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(formatter);
    }

    public static Date toDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatterConstant.DATE_TIME_FORMATTER_DEFAULT);
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date toDate(String str, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatterConstant.DATE_TIME_FORMATTER_DEFAULT);
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
