package com.tensquare.qa;

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
@SpringBootApplication()
@EnableEurekaClient //表示为Eureka客户端
@EnableDiscoveryClient //发现客户端
@EnableFeignClients  //Feign客户端
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
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