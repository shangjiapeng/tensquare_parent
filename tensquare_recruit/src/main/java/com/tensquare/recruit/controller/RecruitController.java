package com.tensquare.recruit.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:37
 */
@RestController
@RequestMapping("/recruit")
@CrossOrigin   //只需要在controller类上添加注解@CrossOrigin 即可解决跨域问题
public class RecruitController {
    @Resource
    private RecruitService recruitService;


    /**
     * 根据状态查询-推荐职位集合
     * 查询状态为2并以创建日期降序排序，查询前4条记录
     * @param state
     * @return
     */
    @RequestMapping(value = "/search/recommendList",method = RequestMethod.GET)
    public Result findRecommendList(String state){
        List<Recruit> recruitList = recruitService.recommendList("2");
        return new Result(true, StatusCode.OK,"查询成功",recruitList);
    }

    /**
     * 查询最新职位集合
     * 查询状态不为0并以创建日期降序排序，查询前12条记录
     */
    @RequestMapping(value = "/search/newList",method = RequestMethod.GET)
    public Result findNewList (){
        List<Recruit> newList = recruitService.newList("0");
        return new Result(true,StatusCode.OK,"查询成功",newList);
    }


}
