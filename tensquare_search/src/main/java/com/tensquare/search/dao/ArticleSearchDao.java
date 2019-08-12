package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>文章搜索数据访问层接口</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-12 12:12
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {

    /**
     * 检索
     *
     * @param * @return
     */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);

}
