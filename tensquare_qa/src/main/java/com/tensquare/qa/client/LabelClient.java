package com.tensquare.qa.client;

import com.tensquare.common.entity.Result;
import com.tensquare.qa.client.impl.LabelClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 11:44
 */
@FeignClient(value = "tensquare-base",fallback = LabelClientImpl.class)  //指定从哪个服务中调用功能,名称与被调用的服务 名保持一致，并且不能包含下划线
public interface LabelClient {


    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)//用于对被调用的微服务进行地址映射。注意 @PathVariable注解一定要指定参数名称，否则出错
    public Result findById(@PathVariable("id") String id);

}
