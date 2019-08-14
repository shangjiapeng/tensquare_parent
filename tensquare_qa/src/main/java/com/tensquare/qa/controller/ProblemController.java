package com.tensquare.qa.controller;

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
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * problem控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private JwtUtil jwtUtil;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
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
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param problem
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Problem problem) {
        Claims claims= (Claims) httpServletRequest.getAttribute("claims_user");
        if (claims==null){
            return new Result(false, StatusCode.ACCESS_ERROR, "无权访问");
        }
        problem.setUserid(claims.getId());
        problemService.add(problem);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据标签ID查询最新回答列表
     *
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/newReplyList/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result newReplyList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemService.findNewReplyListByLabelId(labelId, page, size);
        PageResult<Problem> pageResult = new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据标签ID查询热门问题列表
     *
     * @param labelId
     * @param page    页码
     * @param size    页大小
     * @return
     */
    @RequestMapping(value = "/hotList/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result hotList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemService.findHotListByLabelId(labelId, page, size);
        PageResult<Problem> pageResult = new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据标签ID查询等待回答列表
     * 查询回复数为0的,按照问题创建的时间进行倒序排列
     *
     * @param labelId
     * @param page    页码
     * @param size    页大小
     * @return
     */
    @RequestMapping(value = "/waitList/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result waitList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemService.findWaitListByLabelId(labelId, page, size);
        PageResult<Problem> pageResult = new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }


}
