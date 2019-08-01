package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.tensquare.common.util.IdWorker;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 16:29
 */
@SpringBootApplication()
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,1L);
    }
}
