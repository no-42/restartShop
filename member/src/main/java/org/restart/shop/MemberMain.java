package org.restart.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.restart.shop.mapper")
//@EnableDiscoveryClient
//@RefreshScope
public class MemberMain {
    public static void main(String[] args) {
        SpringApplication.run(MemberMain.class, args);
    }
}
