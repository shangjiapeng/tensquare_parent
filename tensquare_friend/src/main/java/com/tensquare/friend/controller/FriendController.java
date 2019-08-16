package com.tensquare.friend.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 14:09
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    @Resource
    private HttpServletRequest httpServletRequest;

    /**
     * 添加好友
     *
     * @param friendid 对方用户ID
     * @param type     1:喜欢 0:不喜欢
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESS_ERROR, "无权访问");
        }
        //如果是喜欢
        if (type.equals("1")) {
            if (friendService.addFriend(claims.getId(), friendid) == 0) {
                return new Result(false, StatusCode.REPEAT_ERROR, "已经添加此好友");
            }
        } else {
            //不喜欢
            friendService.addNoFriend(claims.getId(), friendid);//向不喜欢的列表中添加记录
        }
        return new Result(true, StatusCode.OK, "操作成功");
    }

    /**
     * 删除好友
     *
     * @param friendid * @return
     */
    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String friendid) {
        Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESS_ERROR, "无权访问");
        }
        friendService.deleteFriend(claims.getId(), friendid);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
