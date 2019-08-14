package com.tensquare.user.controller;

import java.util.HashMap;
import java.util.Map;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * user控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(value = "register/{code}", method = RequestMethod.POST)
    public Result add(@RequestBody User user, @PathVariable String code) {
        userService.add(user, code);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * 删除用户，必须拥有管理员admin权限，否则不能删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        //直接获取从请求头中获取admin声明,如果有的话,说明是管理员的身份执行,这样做和AdminController中的删除方法调用鉴权的操作是同一个作用
        Claims claims=(Claims)httpServletRequest.getAttribute("claims_admin");
        if (claims==null||"".equals(claims)){
            return new Result(true,StatusCode.ACCESS_ERROR,"无权访问");
        }
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");


    }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "sendSms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送短信验证码成功");
    }

    /**
     * 用户登录
     *
     * @param loginMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result findByMobile(@RequestBody Map<String,String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        User user = userService.findByMobileAndPassWord(mobile, password);
        if (user!=null){
            //生成token ,并设置返回
            String token= jwtUtil.createJWT(user.getId(),user.getMobile(),"user");//这里的角色可以通过springSecurity获取
            Map map = new HashMap<>();
            map.put("token",token);
            map.put("name",user.getMobile());//登录名
            map.put("avatar",user.getAvatar());//头像
            map.put("role","user");//角色
            return new Result(true,StatusCode.OK,"登录成功",map);
        }else {
            return new Result(false,StatusCode.LOGIN_ERROR,"用户名或密码错误");
        }
    }

}
