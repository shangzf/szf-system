package com.shangzf.front.common;

import com.shangzf.common.util.UserContextHolder;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

public class UserManager {

    public static final String X_USER_ID = "x-user-id";
    public static final String X_USER_NAME = "x-user-name";
    public static final String X_USER_IP = "x-user-ip";

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        String userId = getItem(X_USER_ID);
        if (NumberUtils.isParsable(userId)) {
            return Long.parseLong(userId);
        }
        return -1L;
    }

    private static String getItem(String itemName) {
        UserContextHolder instance = UserContextHolder.getInstance();
        if (null == instance) {
            return null;
        }
        Map<String, String> context = instance.getContext();
        if (MapUtils.isEmpty(context)) {
            return null;
        }
        return context.get(itemName);
    }

    /**
     * 获取用户名称
     */
    public static String getUserName() {
        String userName = getItem(X_USER_NAME);
        if (StringUtils.isNotBlank(userName)) {
            return userName;
        }
        return StringUtils.EMPTY;
    }

    public static String getUserIp() {
        return getItem(X_USER_IP);
    }

}
