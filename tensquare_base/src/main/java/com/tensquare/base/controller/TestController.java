package com.tensquare.base.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 此测试controller
 * 用于测试springcloud bus 消息总线服务,动态读取远端config配置文件
 * </p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-19 15:12
 */
@RefreshScope  //@RefreshScope 此注解用于刷新配置
@RestController
public class TestController {

    @Value("${sms.ip}")
    private String ip;

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip() {
        return ip;
    }
}
