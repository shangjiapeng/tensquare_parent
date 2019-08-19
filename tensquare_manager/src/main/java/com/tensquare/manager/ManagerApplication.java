package com.tensquare.manager;

import com.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * <p>管理后台微服务网关</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 16:52
 */

@EnableZuulProxy  //开启网关代理
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
