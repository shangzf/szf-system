package com.shangzf.boss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.shangzf")
@EnableFeignClients("com.shangzf")
@EnableDiscoveryClient
@SpringBootApplication
public class BossApplication {
    public static void main(String[] args) {
        SpringApplication.run(BossApplication.class, args);
    }
}
