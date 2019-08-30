package com.tensquare.article_crawler;

import com.tensquare.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-22 14:51
 */
@SpringBootApplication
@EnableScheduling   //开启Schedule 任务
public class ArticleCrawlerApplication {

    @Value("${spring.redis.host}")
    private String redis_host;

    public static void main(String[] args) {
        SpringApplication.run(ArticleCrawlerApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1L, 1L);
    }

    //注入此类是为了方便向redis中存储数据
    @Bean
    public RedisScheduler redisScheduler() {
        return new RedisScheduler(redis_host);
    }

}
