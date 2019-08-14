package com.tensquare.spit;

import com.tensquare.common.util.IdWorker;
import com.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>吐槽,评论模块启动类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-08 16:41
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class SpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
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
