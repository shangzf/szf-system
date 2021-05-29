package com.shangzf.oauth.filter;

import com.google.common.collect.Lists;
import com.shangzf.oauth.multi.MultiAuthentication;
import com.shangzf.oauth.multi.MultiAuthenticationContext;
import com.shangzf.oauth.multi.authenticator.MultiAuthenticator;
import com.shangzf.common.constant.EndpointConstant;
import com.shangzf.common.constant.GrantTypeConstant;
import com.shangzf.common.constant.ParamsConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 复合的过滤器
 */
@Component
public class MultiAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    private Collection<MultiAuthenticator> authenticators;

    private ApplicationContext context;

    private final RequestMatcher requestMatcher;

    public MultiAuthenticationFilter() {
        this.requestMatcher = new OrRequestMatcher(new AntPathRequestMatcher(EndpointConstant.OAUTH_TOKEN, "GET"), new AntPathRequestMatcher(EndpointConstant.OAUTH_TOKEN, "POST"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 判断密码授权
        if (isMatches(httpServletRequest)) {
            // 获取集成登录信息
            MultiAuthentication multiAuthentication = new MultiAuthentication();
            multiAuthentication.setAuthType(httpServletRequest.getParameter(ParamsConstant.AUTH_TYPE));
            multiAuthentication.setAuthParameters(request.getParameterMap());
            // 放入当前线程中
            MultiAuthenticationContext.set(multiAuthentication);
            // 处理
            try {
                prepare(multiAuthentication, httpServletResponse);
                chain.doFilter(request, response);
                complete(multiAuthentication);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            } finally {
                MultiAuthenticationContext.clear();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void complete(MultiAuthentication multiAuthentication) {
        for (MultiAuthenticator multiAuthenticator : authenticators) {
            if (multiAuthenticator.support(multiAuthentication)) {
                multiAuthenticator.complete(multiAuthentication);
            }
        }
    }

    private void prepare(MultiAuthentication multiAuthentication, HttpServletResponse response) {
        // 延迟加载认证器
        if (CollectionUtils.isEmpty(this.authenticators)) {
            synchronized (this) {
                Map<String, MultiAuthenticator> integrationAuthenticatorMap = context
                        .getBeansOfType(MultiAuthenticator.class);
                this.authenticators = integrationAuthenticatorMap.values();
            }
        }
        if (CollectionUtils.isEmpty(this.authenticators)) {
            this.authenticators = Lists.newArrayList();
        }
        for (MultiAuthenticator multiAuthenticator : authenticators) {
            if (multiAuthenticator.support(multiAuthentication)) {
                multiAuthenticator.prepare(multiAuthentication, response);
            }
        }
    }

    private boolean isMatches(HttpServletRequest httpServletRequest) {
        return requestMatcher.matches(httpServletRequest) && StringUtils
                .contains(GrantTypeConstant.PASSWORD, httpServletRequest.getParameter(ParamsConstant.GRANT_TYPE));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
