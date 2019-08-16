package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>用户客户端</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 15:45
 */
@FeignClient("tensquare-user") //指定从哪个服务中调用功能,名称与被调用的服务 名保持一致，并且不能包含下划线
public interface UserClient {

    /**
     * 增加粉丝数
     * @PathVariable注解一定要指定参数名称，否则出错
     * @param userid * @param x
     */
    @RequestMapping(value="/user/incfans/{userid}/{x}",method= RequestMethod.POST)
    public void incFanscount(@PathVariable("userid") String userid, @PathVariable("x") int x);

    /**
     * 增加关注数
     * @PathVariable注解一定要指定参数名称，否则出错
     * @param userid * @param x
     */
    @RequestMapping(value="/user/incfollow/{userid}/{x}",method= RequestMethod.POST)
    public void incFollowcount(@PathVariable("userid") String userid,@PathVariable("x") int x);
}
