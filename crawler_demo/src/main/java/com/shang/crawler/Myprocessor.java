package com.shang.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * <p>爬取类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-21 18:16
 */
public class Myprocessor implements PageProcessor {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Myprocessor.class);

    public void process(Page page) {
        //添加目标地址
//        page.addTargetRequests(page.getHtml().links().all()); //将点前页面里面的说有的链接都添加导目标页面中去
//        System.out.println(page.getHtml().xpath("//*[@id=\"nav\"]/div/div/ul/li[5]/a").toString());
        //目标地址正则匹配
//        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9-]+/article/details/[0-9]{8}").all() );
//        System.out.println(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").toString());
        //ConsolePipeline 控制台输出
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9-]+/article/details/[0-9]{8}").all() );
        page.putField("title",page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").toString());

//        System.out.println(page.getHtml().toString());//打印html

    }

    public Site getSite() {
        //初始化站点
        Site site = Site.me();
        //配置参数
        site.setCharset("utf-8")//设置编码
                .setTimeOut(3000) //设置超时时间,单位时毫秒
                .setRetryTimes(3)//设置重试次数
                .setCycleRetryTimes(3)//设置循环重试次数
//              .addCookie("dotcomt_user", "code4craft")//添加一条 cookie
//              .setDomain("github.com")//设置域名,需要设置域名后addCookie()才可以生效
//              .addHeader("Referer","https://github.com")//添加一个请求头
                .setSleepTime(100)
                .setRetrySleepTime(3);
        return site;
    }


    //Spider是爬虫启动的入口。在启动爬虫之前，我们需要使用一个PageProcessor创建 一个Spider对象，然后使用run()进行启动。
    public static void main(String[] args) {
        //初始化一个spider
        Spider spider = Spider.create(new Myprocessor());
        //添加初始url
//        spider.addUrl("https://blog.csdn.net/nav/ai")
        spider.addUrl("https://blog.csdn.net")
                .addPipeline(new ConsolePipeline())  //ConsolePipeline 控制台输出
                .addPipeline(new FilePipeline("/Users/shangjiapeng/desktop"))  // FilePipeline 文件保存
                .addPipeline(new JsonFilePipeline("/Users/shangjiapeng/desktop/json"))  // 以json方式保存
                .addPipeline(new MyPipeline())  //定制化输出
                .setScheduler(new QueueScheduler())  //使用setScheduler来设置Scheduler
                .setScheduler(new FileCacheQueueScheduler("/Users/shangjiapeng/desktop/scheduler"))//设置文件队列
                .setScheduler(new RedisScheduler("127.0.0.1"))//设置Redis队列
                .thread(3)//开启3个线程
//                .start()//异步启动，当前线程继续执行
//                .runAsync()//异步启动，当前线程继续执行
                .run();//启动，会阻塞当前线程执行

    }
}
