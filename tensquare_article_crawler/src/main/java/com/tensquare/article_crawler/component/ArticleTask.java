package com.tensquare.article_crawler.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;

/**
 * <p>文章任务类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-28 16:37
 */
@Component
public class ArticleTask {

    @Resource
    private ArticleDbPipeline articleDbPipeline;

    @Resource
    private ArticleTxtPipeline articleTxtPipeline;

    @Resource
    private RedisScheduler redisScheduler;



    /**
     * 爬取AI数据
     *
     * @Scheduled(cron = "0 * 16 * * ?")  //每天下午4点的每分钟发一次通知
     */
    @Scheduled(cron = "0 * 15 * * ?")
    public void aiTask() {
        System.out.println("爬取AI文章");
        Spider spider = Spider.create(new ArticleProcessor());
        spider.addUrl("https://blog.csdn.net/nav/ai");
        articleTxtPipeline.setChannelId("ai");
        spider.addPipeline(articleTxtPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
        spider.stop();
    }

    /**
     * 爬取db数据
     */
    @Scheduled(cron = "0 * 15 * * ?")
    public void dbTask() {
        System.out.println("爬取DB文章");
        Spider spider = Spider.create(new ArticleProcessor());
        spider.addUrl("https://blog.csdn.net/nav/db");
        articleTxtPipeline.setChannelId("db");
        spider.addPipeline(articleTxtPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
        spider.stop();
    }

    /**
     * 爬取web数据
     */
    @Scheduled(cron = "0 * 15 * * ?")
    public void webTask() {
        System.out.println("爬取WEB文章");
        Spider spider = Spider.create(new ArticleProcessor());
        spider.addUrl("https://blog.csdn.net/nav/web");
        articleTxtPipeline.setChannelId("web");
        spider.addPipeline(articleTxtPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
        spider.stop();
    }
}
