package com.tensquare.ai.Service;

import com.tensquare.ai.util.CnnUtil;
import com.tensquare.common.util.IKUtil;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * <p>人工智能分类模型</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-09-05 17:51
 */
@Service
public class CnnService {

    //词向量模型
    @Value("${ai.vecModel}")
    private String vecModel;

    //训练模型结果保存路径
    @Value("${ai.cnnModel}")
    private String cnnModel;

    //爬虫分词后的数据路径
    @Value("${ai.dataPath}")
    private String dataPath;


    /**
     * 创建卷积模型
     */
    public boolean build() {
        try {
            //创建计算图对象
            ComputationGraph graph = CnnUtil.createComputationGraph(100);
            //加载词向量 训练数据集
            WordVectors wordVectors = WordVectorSerializer.loadStaticModel(new File(vecModel));
            String[] childPaths = {"java", "ai", "web"};
            DataSetIterator trainIterator = CnnUtil.getDataSetIterator(dataPath, childPaths, wordVectors, 32, 256, new Random(12345));
            //模型训练
            graph.fit(trainIterator);
            //保存模型之前先删除
            new File(cnnModel).delete();
            //保存模型
            ModelSerializer.writeModel(graph, cnnModel, true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 实现智能分类
     * 返回map集合 分类与百分比
     * @param content
     */
    public Map textClassify(String content){
        System.out.println("content:"+ content);
        try {
            //分词
            content= IKUtil.split(content," ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] childPaths={"java", "ai", "web"};
        //获取语言结果
        Map map =null;

        try {
            map = CnnUtil.predictions(vecModel, cnnModel, dataPath, childPaths, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

}
