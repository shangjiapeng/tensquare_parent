package com.tensquare.base.controller;

import com.tensquare.recruit.entity.PageResult;
import com.tensquare.recruit.entity.Result;
import com.tensquare.recruit.entity.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>标签控制层</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 18:17
 */
@RestController
@RequestMapping("/label")
@CrossOrigin   //只需要在controller类上添加注解@CrossOrigin 即可解决跨域问题
public class LabelController {

    @Resource
    private LabelService labelService;

    /**
     * 查询全部列表
     *
     * @return list
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll() {
        List<Label> labelList = labelService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", labelList);
    }

    /**
     * 根据id查询
     *
     * @param id Long
     * @return com.tensquare.base.pojo
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * 添加标签
     *
     * @param label com.tensquare.base.pojo
     * @return Result
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result<>(true, StatusCode.OK, "添加成功", null);
    }

    /**
     * 删除标签
     *
     * @param id Long
     * @return Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result<>(true, StatusCode.OK, "删除成功", null);
    }

    /**
     * 修改更新标签
     *
     * @param label com.tensquare.base.pojo
     * @return Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result<>(true, StatusCode.OK, "修改成功", null);
    }

    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result<List> findBySearchMap(@RequestBody Map searchMap) {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findBySearchMap(searchMap));
    }

    /**
     * 条件+分页查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findBySearchMap(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Label> labelPage = labelService.findBySearchMap(searchMap, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", new PageResult<>(labelPage.getTotalElements(),labelPage.getContent()));
    }


}
