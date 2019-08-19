package com.tensquare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <p>springcloud集中配置启动类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-19 11:53
 */
@EnableConfigServer  // 开启集中配置服务
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
