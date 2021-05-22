package com.shangzf.gateway.filter;

import com.shangzf.authority.api.service.IAuthService;
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

/**
 * 请求url权限
 */
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
        LinkedHashSet<URI> attribute = exchange
                .getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);


        return null;
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
