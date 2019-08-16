package com.tensquare.sms;

import com.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * <p>短信发送模块启动类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-08 16:41
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }

}
