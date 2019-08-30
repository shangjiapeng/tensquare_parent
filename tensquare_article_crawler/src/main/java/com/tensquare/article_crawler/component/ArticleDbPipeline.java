package com.tensquare.article_crawler.component;

import com.tensquare.article_crawler.dao.ArticleDao;
import com.tensquare.article_crawler.pojo.Article;
import com.tensquare.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * <p>文章入库类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-23 18:33
 */
@Component
public class ArticleDbPipeline implements Pipeline {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    private String channelId;//频道ID

    public void setChannelId(String channelId){
        this.channelId=channelId;
    }

    /**
     * Process extracted results.
     * ReusltItems 相当于一个Map，它保存PageProcessor处理的结果，供Pipeline使用。
     * 它的 API与Map很类似，值得注意的是它有一个字段 skip ，若设置为true，则不应被Pipeline处理
     * @param resultItems resultItems
     * @param task        task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {

        String title = resultItems.get("title");
        String content = resultItems.get("content");
        Article article = new Article();
        article.setId(idWorker.nextId()+"");
        article.setChannelid(channelId);
        article.setTitle(title);
        article.setContent(content);
        //保存
        articleDao.save(article);
        System.out.println("<<<<执行像数据库中添加文章的操作>>>>");
    }
}
