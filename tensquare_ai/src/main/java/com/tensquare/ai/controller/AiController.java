package com.tensquare.ai.controller;

import com.tensquare.ai.Service.CnnService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-09-05 18:36
 */
@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private CnnService cnnService;

    /**
     * 因为文本内容会很长,因此采用post请求发送
     * @param content
     * @return
     */
    @RequestMapping(value = "/textclassify", method = RequestMethod.POST)
    public Map textClassify(@RequestBody Map<String, String> content) {
        return cnnService.textClassify(content.get("content"));
    }

}
