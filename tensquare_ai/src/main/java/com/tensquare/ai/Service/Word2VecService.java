package com.tensquare.ai.Service;

import com.tensquare.common.util.FileUtil;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>service</p>
 * 把分词文本内容训练成机器符号
 * 创建可以输入深度学习,神经网络的神经词向量
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 16:43
 */
@Service
public class Word2VecService {

    //分词模型路径
    @Value("${ai.wordLib}")
    private String wordLib;

    //爬虫分词后的数据路径
    @Value("${ai.dataPath}")
    private String dataPath;

    //训练模型保存的路径
    @Value("${ai.vecModel}")
    private String vecModel;

    /**
     * 合并
     */
    public void mergeWord() {
        try {
            List<String> fileNames = FileUtil.getFiles(dataPath);
            if (fileNames != null) {
                FileUtil.merge(wordLib, fileNames);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据分词模型生成词向量模型
     *
     *参数说明:
     *      minWordFrequency是一个词在语料中必须出现的最少次数。本例中出现不到5次的词都不予学习。
     *          词语必须在多种上下文中出现，才能让模型学习到有用的特征。对于规模很大的语料库，理应提高出现次数的下限。
     *      iterations是网络在处理一批数据时允许更新系数的次数。迭代次数太少，网络可能来不及学习所有能学到的信息;迭代次数太多则会导致网络定型时间变长。
     *      layerSize指定词向量中的特征数量，与特征空间的维度数量相等。以500个特征值表 示的词会成为一个500维空间中的点。
     *      windowSize:表示当前词与预测词在一个句子中的最大距离是多少
     *      seed:用于随机数发生器。与初始化词向量有关
     *      iterate: 告知网络当前定型的是哪一批数据集。
     *      vec.fit() 让已配置好的网络开始定型。
     */
    public boolean build() {

        boolean flag = false;
        try {
            //加载爬虫数据集
            SentenceIterator iterator = new LineSentenceIterator(new File(wordLib));
            Word2Vec vec = new Word2Vec.Builder()
                    .minWordFrequency(5)
                    .iterations(1)
                    .layerSize(100)
                    .windowSize(5)
                    .seed(42)
                    .iterate(iterator)
                    .build();
            vec.fit();
            //保存模型之前先删除
            new File(vecModel).delete(); //删除
            WordVectorSerializer.writeWordVectors(vec,vecModel);
            flag=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;


    }


}
