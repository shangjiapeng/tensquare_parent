package com.tensquare.article_crawler.component;

import com.tensquare.common.util.HTMLUtil;
import com.tensquare.common.util.IKUtil;
import com.tensquare.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


/**
 * <p>文章写入文本文件类</p>
 * 入库类(分词后保存为txt)
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-28 16:38
 */
@Component
public class ArticleTxtPipeline implements Pipeline {


    @Value("${ai.datapath}")

    private String dataPath; //存储路径

    private String channelId; //频道ID

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * Process extracted results.
     *
     * @param resultItems resultItems
     * @param task        task
     */
    @Override

    public void process(ResultItems resultItems, Task task) {

        String title = resultItems.get("title");//获取标题
        String content = HTMLUtil.delHTMLTag(resultItems.get("content"));  //获取正文并删除html标签
        //将标题+正文分词后保存到相应的文件夹
        try {
            PrintWriter printWriter = new PrintWriter(new File(dataPath + "/" + channelId + "/" + UUID.randomUUID() + ".txt"));
            printWriter.print(IKUtil.split(title + " " + content, " "));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
