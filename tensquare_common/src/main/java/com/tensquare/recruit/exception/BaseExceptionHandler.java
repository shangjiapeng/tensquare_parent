package com.tensquare.recruit.exception;

import com.tensquare.recruit.entity.Result;
import com.tensquare.recruit.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>统一异常处理类</p>
 * 为了使我们的代码更容易维护，我们创建一个类集中处理异常
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 14:43
 */
@ControllerAdvice
//@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR, e.getMessage(), null);
    }
}
