package com.tensquare.qa.interceptor;

import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>JWT拦截器类</p>
 * Spring为我们提供了 org.springframework.web.servlet.handler.HandlerInterceptorAdapter这个适配器,
 * 继承此类，可以非常方便的实现自己的拦截器。
 * 他有三个方法:
 * 分别实现预处理、后处理(调用了Service并返回ModelAndView，但未进行页面渲 染)、返回处理(已经渲染了页面)
 * 在preHandle中，可以进行编码、安全控制等处理;
 * 在postHandle中，有机会修改ModelAndView;
 * 在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
 * @Author: ShangJiaPeng
 * @Since: 2019-08-13 14:14
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //无论如何都放行,具体能不能操作还是在据图的操作中去判断
        //拦截器只是负责把请求头中包含token的令牌进行一个解析验证
        final String authHeader = request.getHeader("Authorization");
        //如果有包含有Authorization头信息，就对其进行解析
        if (authHeader != null && !"".equals(authHeader) && authHeader.startsWith("Admin ")) {
            //得到token
            final String token = authHeader.substring(6);// Admin 后面的部分
            try {
                //对令牌进行验证,对request携带的信息进行增强(使用了代理模式)
                Claims claims = jwtUtil.parseJWT(token);
                String roles = (String) claims.get("roles");
                if (roles != null && "admin".equals(roles)) { //如果是管理员
                    request.setAttribute("claims_admin", claims);
                }
                if (roles != null && "user".equals(roles)) { //如果是普通用户登录
                    request.setAttribute("claims_user", claims);
                }
            } catch (Exception e) {
                throw new RuntimeException("令牌不正确！");
            }
        }
        return true;
    }

}
