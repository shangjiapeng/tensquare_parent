package com.tensquare.recruit.controller;

import com.tensquare.recruit.service.RecruitService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:37
 */
@RestController
@RequestMapping("/label")
@CrossOrigin   //只需要在controller类上添加注解@CrossOrigin 即可解决跨域问题
public class RecruitController {
    @Resource
    private RecruitService recruitService;

}
