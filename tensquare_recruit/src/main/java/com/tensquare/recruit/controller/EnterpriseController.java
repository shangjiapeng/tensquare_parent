package com.tensquare.recruit.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.recruit.service.EnterpriseServcie;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>企业控制层</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:37
 */
@RestController
@RequestMapping("/enterprise")
@CrossOrigin   //只需要在controller类上添加注解@CrossOrigin 即可解决跨域问题
public class EnterpriseController {
    @Resource
    private EnterpriseServcie enterpriseServcie;

    /**
     * 根据热门状态获取企业列表
     * @return
     */
    @RequestMapping(value="/search/hotlist",method= RequestMethod.GET)
    public Result findHotlist(){
        return new Result(true, StatusCode.OK, "查询成功", enterpriseServcie.findByIshot());
    }
}
