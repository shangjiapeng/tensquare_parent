package com.tensquare.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Ai 人工智能模块+</p>
 * DeepLearning4J(DL4J)是一套基于Java语言的神经网络工具包，可以构建、定型 和部署神经网络。
 * DL4J与Hadoop和Spark集成，支持分布式CPU和GPU，为商业环境 (而非研究工具目的)所设计。Skymind是DL4J的商业支持机构。
 * https://deeplearning4j.org/cn/index.html
 *
 * Word2vec是一种比较流行的自然语言算法，能创建可以输入深度神经网络的神经词向量
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 16:15
 */
@SpringBootApplication
@EnableScheduling
public class AiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiApplication.class,args);
    }
}
