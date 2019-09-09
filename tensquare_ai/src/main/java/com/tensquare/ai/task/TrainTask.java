package com.tensquare.ai.task;

import com.tensquare.ai.Service.CnnService;
import com.tensquare.ai.Service.Word2VecService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>模型训练任务类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 16:53
 */
@Component
public class TrainTask {

    @Resource
    private Word2VecService word2VecService;
    @Resource
    private CnnService cnnService;

    /**
     * 训练模型
     */
    @Scheduled(cron = "0 0 15 * * ?")
    public void trainModel(){
        System.out.println("开始合并语料库....");
        word2VecService.mergeWord();
        System.out.println("合并语料库结束!");

        System.out.println("开始构建词向量模型....");
        word2VecService.build();
        System.out.println("构建词向量模型结束!");

        System.out.println("开始构建神经网络卷积模型....");
        cnnService.build();
        System.out.println("构建神经网络卷积模型结束!");

    }

}
