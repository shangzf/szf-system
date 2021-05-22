package com.shangzf.gateway.filter;

import com.shangzf.authority.api.service.IAuthService;
import com.shangzf.common.vo.constant.AuthenticationConstant;
import com.shangzf.common.vo.constant.UserManagerConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * 请求url权限
 */
@Slf4j
@Configuration
public class AccessGatewayFilter implements GlobalFilter {

    private static final String BOSS_PATH_PREFIX = "/boss";
    @Autowired
    private IAuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().value();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        LOGGER.info("Access filter, url: {}, access_token:{}", url, authorization);
        // 验证是否有权限
        if (StringUtils.isNotBlank(authorization)) {
            return validateAuthentication(exchange, chain, authorization, url);
        }
        // 判断地址是否不需要验证
        if (authService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }
        // 无权限访问
        return unauthorized(exchange);
    }

    private Mono<Void> validateAuthentication(ServerWebExchange exchange, GatewayFilterChain chain, String authorization, String url) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        String ip = request.getRemoteAddress().getAddress().getHostAddress();
        // 获取原始的url
        LinkedHashSet<URI> originUrl = exchange
                .getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);

        String userId = "";
        String userName = "";
        // 处理JWT
        try {
            Jws<Claims> jwt = authService.getJwt(authorization);
            if (Objects.nonNull(jwt) && Objects.nonNull(jwt.getBody())) {
                userId = String.valueOf(jwt.getBody().get(AuthenticationConstant.USER_ID));
                userName = String.valueOf(jwt.getBody().get(AuthenticationConstant.USER_ID));
                // 拼装用户id、用户名放到请求里面
                ServerHttpRequest.Builder builder = request.mutate();
                if (StringUtils.isNotBlank(userName)) {
                    builder.header(UserManagerConstant.X_USER_NAME, userName);
                }
                if (StringUtils.isNotBlank(userId)) {
                    builder.header(UserManagerConstant.X_USER_ID, userId);
                }
                if (StringUtils.isNotBlank(ip)) {
                    builder.header(UserManagerConstant.X_USER_IP, ip);
                }
                exchange = exchange.mutate().request(builder.build()).build();
                log.info("userId:{}, userName:{}, access_token:{}, url:{}", userId, userName, authorization, url);
            }
        } catch (Exception e) {
            log.error("user token error :{}", e.getMessage());
            // 如果不是忽略url，则返回401，需要登录
            if (!authService.ignoreAuthentication(url)) {
                return unauthorized(exchange);
            }
        }

        // 如果是忽略的url，在填充header中的登录用户信息后直接返回
        if (authService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }

        // 管理后端需要权限操作，其他时候只需要验证JWT是否正常
        boolean hasPermission = true;
        if (isBossPath(originUrl, url)) {
            // 将原始url赋值给当前url。
            url = BOSS_PATH_PREFIX.concat(url);
            hasPermission = authService.authenticate(authorization, userId, url, method);
            log.info("Check boss permission. userId:{}, have permission:{}, url:{}, method:{}", userId, hasPermission, url, method);
        }
        if (hasPermission && StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userName)) {
            log.info("User can access. userId:{}, userName:{}, url:{}, method:{}", userId, userName, url, method);
            return chain.filter(exchange);
        }
        return forbidden(exchange);
    }

    /**
     * 根据原始url判断是否请求的后台管理功能url。
     * <p>filter中如果使用了StripPrefix，url会被截取前一个"/"节点。无法检测到url是否包含/boss。这里获取原始的url进行判断</p>
     *
     * @param originUrl 获取的原始url
     * @param url       通过一些filter处理过的url。
     */
    private boolean isBossPath(LinkedHashSet<URI> originUrl, String url) {
        if (url.startsWith(BOSS_PATH_PREFIX)) {
            return true;
        }
        for (URI uri : originUrl) {
            if (uri.getPath().startsWith(BOSS_PATH_PREFIX)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 未通过权限验证，返回forbidden
     */
    private Mono<Void> forbidden(ServerWebExchange exchange) {
        return rebuildExchange(exchange, HttpStatus.FORBIDDEN);
    }

    /**
     * 未登录或token状态异常，返回401
     */
    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        return rebuildExchange(exchange, HttpStatus.UNAUTHORIZED);
    }

    private Mono<Void> rebuildExchange(ServerWebExchange exchange, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(httpStatus.getReasonPhrase().getBytes());
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}
