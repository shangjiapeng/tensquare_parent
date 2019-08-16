package com.tensquare.friend;

import com.tensquare.common.util.IdWorker;
import com.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-01 10:38
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,1L);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}