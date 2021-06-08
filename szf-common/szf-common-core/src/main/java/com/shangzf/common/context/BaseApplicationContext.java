package com.shangzf.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BaseApplicationContext implements ApplicationContextAware, EnvironmentAware {

    private static ApplicationContext _applicationContext;
    private static Environment _environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        _applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        _environment = environment;
    }

    public static ApplicationContext getApplicationContext() {
        return _applicationContext;
    }

    public static Environment get_environment() {
        return _environment;
    }
}
