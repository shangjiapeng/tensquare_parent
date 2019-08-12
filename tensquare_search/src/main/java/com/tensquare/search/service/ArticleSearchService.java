package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-12 12:13
 */
@Service
public class ArticleSearchService {

    @Resource
    private ArticleSearchDao articleSearchDao;

    /**
     * 增加文章
     *
     * @param article
     */
    public void save(Article article) {
        articleSearchDao.save(article);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(String id){
        articleSearchDao.deleteById(id);
    }

    /**
     * 检索
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String keywords, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleSearchDao.findByTitleOrContentLike(keywords, keywords, pageRequest);
    }
}
