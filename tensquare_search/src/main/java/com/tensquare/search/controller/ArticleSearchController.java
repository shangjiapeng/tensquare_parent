package com.tensquare.search.controller;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-12 12:16
 */
@RestController
@CrossOrigin  //跨域支持
@RequestMapping("/article")
public class ArticleSearchController {

    @Resource
    private ArticleSearchService articleSearchService;

    /**
     * 添加索引
     *
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        articleSearchService.save(article);
        return new Result(true, StatusCode.OK, "添加索引成功");
    }

    /**
     * 根据id删除
     * @param id
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public void deleteById(String id){
        articleSearchService.deleteById(id);
    }

    /**
     * 检索 根据标题或者文章内容模糊查询
     */
    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByTitleOrContentLike(@PathVariable String keywords,@PathVariable int page,@PathVariable int size){
        Page<Article> articlePage = articleSearchService.findByTitleOrContentLike(keywords, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(articlePage.getTotalElements(),articlePage.getContent()));
    }
}
