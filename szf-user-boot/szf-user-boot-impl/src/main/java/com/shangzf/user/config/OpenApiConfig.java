package com.shangzf.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "自研系统", description = "用户接口文档", version = "v0.0.1"))
@SecurityScheme(name = "Authorization", type = SecuritySchemeType.HTTP, scheme = "bearer", description = "输入access_token的值", bearerFormat = "JWT")
public class OpenApiConfig {
}
