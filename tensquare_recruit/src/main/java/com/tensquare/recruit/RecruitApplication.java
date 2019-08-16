package com.tensquare.recruit;

import com.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * <p>招聘</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 16:29
 */
@SpringBootApplication()
@EnableEurekaClient
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,1L);
    }
}
