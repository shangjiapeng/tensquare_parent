package com.tensquare.user_crawler.component;

import com.tensquare.user_crawler.utils.DownLoadUtil;
import com.tensquare.user_crawler.utils.IdWorker;
import com.tensquare.user_crawler.dao.UserDao;
import com.tensquare.user_crawler.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;

/**
 * <p>用户数据入库管道类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-29 16:01
 */
@Component
public class UserPipeline implements Pipeline {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;

    /**
     * Process extracted results.
     *
     * @param resultItems resultItems
     * @param task        task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        User user = new User();
        user.setId(idWorker.nextId()+"");
        user.setNickname(resultItems.get("nickname"));
        String imageUrl =resultItems.get("image");//图片地址
        //只要路径名称最后一个/后面的部分
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        user.setAvatar(fileName);
        userDao.save(user);
        //下载图片
        try {
            DownLoadUtil.download(imageUrl,fileName,"/Users/shangjiapeng/userimages");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
