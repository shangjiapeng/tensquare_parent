package com.tensquare.user_crawler.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;

/**
 * <p>用户数据爬取任务类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 11:07
 */
@Component
public class UserCrawlerTask {

    @Resource
    private RedisScheduler redisScheduler;
    @Resource
    private UserPipeline userPipeline;

    @Scheduled(cron = "0 * 15 * * ?")
    public void userTask(){
        System.out.println("开始爬取用户数据...");
        Spider spider = Spider.create(new UserProcessor());
        spider.addUrl("https://blog.csdn.net");
        spider.addPipeline(userPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
        spider.stop();
    }
}
