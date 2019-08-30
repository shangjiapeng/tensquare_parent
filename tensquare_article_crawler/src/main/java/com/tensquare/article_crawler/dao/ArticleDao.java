package com.tensquare.article_crawler.dao;

import com.tensquare.article_crawler.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * article数据访问接口
 * @Modifying注解
 * 　　1、在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作。
 * 　　2、UPDATE或者DELETE操作需要使用事务，此时需要 定义Service层，在Service层的方法上添加事务操作 @Transactional。
 * 　　3、注意JPQL不支持INSERT操作。
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    /**
     * 审核
     *
     * @param id
     */
    @Modifying //通知jpa这是一个update或者delete操作，jpql不支持insert操作
    @Query("update Article set state= '1' where id = ?1 ")
    public void examine(String id);

    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @Modifying
    @Query("update Article set thumbup=thumbup+1 where id=?1")
    public int updateThumbup(String id);


}
