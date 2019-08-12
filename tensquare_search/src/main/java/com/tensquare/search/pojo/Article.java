package com.tensquare.search.pojo;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * <p></p>
 * 是否索引: 就是看改字段是否能够被搜索
 * 是否分词: 搜索的时候是整体匹配还是单词匹配
 * 是否展示: 就是是否显示在页面上
 * @Author: ShangJiaPeng
 * @Since: 2019-08-12 11:55
 */
@Data
@ToString
@Document(indexName = "tensquare", type = "article")
public class Article {
    @Id
    private String id;//ID

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart_word")
    private String title;//标题

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart_word")
    private String content;//文章正文

    private String state;//审核状态

}
