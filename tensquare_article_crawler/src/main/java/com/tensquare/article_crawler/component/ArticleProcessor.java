package com.tensquare.article_crawler.component;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * <p>文章爬取类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-23 18:29
 */
@Component
public class ArticleProcessor implements PageProcessor {

    /**
     * process the page, extract urls to fetch, extract the data and store
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{8}").all());
        String title = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1/text()").get();
        String content = page.getHtml().xpath("//*[@id=\"article_content]").get();
        //获取页面需要的内容
        System.out.println("标题:" + title);
        System.out.println("内容:" + content);
        if (title != null && content != null) { //如果有标题和内容
            page.putField("title", title);
            page.putField("content", content);
        } else {
            page.setSkip(true);//跳过
        }
    }

    /**
     * get the site settings
     *
     * @return site
     * @see Site
     */
    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3000).setSleepTime(100);
    }
}
