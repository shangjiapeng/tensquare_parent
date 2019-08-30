package com.shang.crawler;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * <p>定制Pipeline</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-22 14:19
 */
public class MyPipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
       String title =resultItems.get("title");
        System.out.println("我定制的 title:"+ title);
    }
}
