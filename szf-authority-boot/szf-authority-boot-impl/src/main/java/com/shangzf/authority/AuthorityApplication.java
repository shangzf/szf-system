package com.shangzf.authority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.shangzf")
@MapperScan("com.shangzf.authority.mapper")
public class AuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }
}
