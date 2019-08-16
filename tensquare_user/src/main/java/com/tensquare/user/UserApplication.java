package com.tensquare.user;

import com.tensquare.common.util.IdWorker;
import com.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-01 10:38
 */
@SpringBootApplication()
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

    /**
     * 唯一id生成
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,1L);
    }

    /**
     * BCrypt强哈希方法来加密密码
     * @return
     */
    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入jwt工具类
     * @return
     */
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}