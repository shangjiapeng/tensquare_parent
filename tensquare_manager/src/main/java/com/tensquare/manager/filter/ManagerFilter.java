package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>过滤器类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 17:39
 */
@Component
public class ManagerFilter extends ZuulFilter {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {//过滤器类型
        return "pre";//前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;//优先级，数字越小,越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true;//过滤器开关，true表示开启
    }

    /**
     * 过滤器内执行的操作,return 任何的object值都表示继续执行
     * setSendZuulResponse(false);表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {   //动态代理,方法增强
        System.out.println("经过Zuul过滤器");
        //使用ZUUL内置RequestContext对象获取请求的上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = requestContext.getRequest();
        //判断(OPTIONS 时ZUUL自己在分发请求时的一个方法)
        if (request.getMethod().equals("OPTIONS")) {//判断请求的方式
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0) {
            System.out.println("登陆页面" + url);
            return null;
        }
        String authHeader = (String) request.getHeader("Authorization");// 获取头信息
        if (authHeader != null && authHeader.startsWith("Admin ")) {
            //token
            String token = authHeader.substring(6);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    if ("admin".equals(claims.get("roles"))) {
                        //添加zuul网关请求头
                        requestContext.addZuulRequestHeader("Authorization", authHeader);
                        System.out.println("token 验证通过，添加了头信息" + authHeader);
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                requestContext.setSendZuulResponse(false);//终止运行
            }
        }
        requestContext.setSendZuulResponse(false);//令zuul过滤该请求，不对其进行路由
        requestContext.setResponseStatusCode(StatusCode.ACCESS_ERROR);// 设置了其返回的错误码
        requestContext.setResponseBody("无权访问"); //设置响应体
        requestContext.getResponse().setContentType("text/html;charset=UTF‐8");
        return null;
    }
}
