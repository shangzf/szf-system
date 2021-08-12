package com.shangzf.common.user;

import com.shangzf.common.constant.StringConstant;
import com.shangzf.common.constant.UserManagerConstant;
import com.shangzf.common.util.UserContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

public class UserManager {

    public static String getUsername() {
        String username = getItem(UserManagerConstant.X_USER_NAME);
        return StringUtils.isBlank(username) ? UserManagerConstant.ANONYMOUS : username;
    }

    public static Long getUserId() {
        String userId = getItem(UserManagerConstant.X_USER_NAME);
        if (!NumberUtils.isParsable(userId)) {
            userId = StringConstant.ZERO;
        }
        return Long.parseLong(userId);
    }

    public static String getUserIp() {
        return getItem(UserManagerConstant.X_USER_IP);
    }

    private static String getItem(String itemKey) {
        return Optional.ofNullable(UserContextHolder.getInstance()).map(UserContextHolder::getContext)
                       .map(context -> context.get(itemKey)).orElse(StringConstant.EMPTY);
    }
}
