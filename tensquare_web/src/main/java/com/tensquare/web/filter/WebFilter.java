package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>zuul过滤器</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 17:01
 */
public class WebFilter extends ZuulFilter {

    /**
     * filterType:
     *     pre :可以在请求被路由之前调用
     *     route :在路由请求时候被调用
     *     post :在route和error过滤器之后被调用
     *     error :处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";  //前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;   //优先级为0,数字越大,优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return false;  //是否执行该过滤器,此处为true,说明需要过滤
    }

    /**
     * 由于经过网关只会,request中的头信息会丢失
     * 过滤器中接收header，转发给微服务
     * 如果有token，直接转发
     * @return
     * @throws ZuulException
     */
    @Override //过滤器的具体逻辑
    public Object run() throws ZuulException {
        System.out.println("经过zuul过滤器...");
        //得到request的上下文(使用ZUUL内置的RequestContext 对象获取)
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = requestContext.getRequest();
        //获取header
        String authorization = request.getHeader("Authorization");
        if (authorization!=null){
            //添加网关鉴权请求头
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
