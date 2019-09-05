package com.tensquare.user_crawler.component;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * <p>用户数据爬取类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-29 12:22
 */
@Component
public class UserProcessor implements PageProcessor {

    /**
     * process the page, extract urls to fetch, extract the data and store
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> requestList = html.links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{8}").all();
        page.addTargetRequests(requestList);
        String nickname = html.xpath("//*[@id=\"uid\"]/text()").get();
        String image = html.xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a/img[1]").css("img", "src").toString();
        if (nickname != null && image != null) {//如果有昵称和头像
            page.putField("nickname", nickname);
            page.putField("image", image);
        }else {
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
