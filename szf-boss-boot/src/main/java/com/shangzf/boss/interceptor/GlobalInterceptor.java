package com.shangzf.boss.interceptor;

import com.shangzf.common.constant.UserManagerConstant;
import com.shangzf.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader(UserManagerConstant.X_USER_ID);
        String userName = request.getHeader(UserManagerConstant.X_USER_NAME);
        String userIp = request.getHeader(UserManagerConstant.X_USER_IP);

        Map<String, String> params = new HashMap<>(3);
        params.put(UserManagerConstant.X_USER_ID, userId);
        params.put(UserManagerConstant.X_USER_NAME, userName);
        params.put(UserManagerConstant.X_USER_IP, userIp);

        UserContextHolder.getInstance().setContext(params);
        log.info("get userId:{}, userName:{} from header", userId, userName);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder instance = UserContextHolder.getInstance();
        if (null != instance) {
            Map<String, String> context = instance.getContext();
            if (MapUtils.isNotEmpty(context)) {
                context.clear();
            }
            instance.setContext(null);
        }
    }
}
