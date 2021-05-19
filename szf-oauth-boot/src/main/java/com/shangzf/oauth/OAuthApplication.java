package com.shangzf.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.shangzf")
@EnableDiscoveryClient
@SpringBootApplication
public class OAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
