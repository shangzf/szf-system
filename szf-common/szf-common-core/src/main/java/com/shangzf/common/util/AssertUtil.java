package com.shangzf.common.util;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * 验证工具
 *
 * @author zefeng.shang@changhong.com
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class AssertUtil {

    public static void state(boolean expression, String message) {
        if ((!expression)) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void hasLength(String text, String message) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(String text, Supplier<String> messageSupplier) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void hasText(String text, String message) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasText(String text, Supplier<String> messageSupplier) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return (Objects.nonNull(messageSupplier) ? messageSupplier.get() : null);
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
