package com.tensquare.gathering;

import com.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-01 10:38
 */
@SpringBootApplication()
@EnableCaching   //开启缓存的支持
public class GatheringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,1L);
    }
}