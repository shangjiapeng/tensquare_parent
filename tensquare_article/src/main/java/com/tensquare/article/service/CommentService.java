package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import com.tensquare.common.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-09 11:28
 */
@Service
public class CommentService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private IdWorker idWorker;

    /**
     * 添加评论
     * @param comment
     */
    public void add(Comment comment) {
        comment.setId(idWorker.nextId() + "");
        commentDao.save(comment);
    }

    /**
     * 根据文章的id查询评论列表
     * @param articleid
     * @return
     */
    public List<Comment> findByArticleId(String articleid){
        return commentDao.findByArticleid(articleid);

    }
}
