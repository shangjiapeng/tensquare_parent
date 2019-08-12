package com.tensquare.spit.controller;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>吐槽,评论控制层</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-08 17:02
 */
@RestController
@CrossOrigin   //支持跨域访问
@RequestMapping("/spit")
public class SpitController {

    @Resource
    private SpitService spitService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Spit> spitList = spitService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", spitList);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    /**
     * 添加
     *
     * @param spit
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result insert(@RequestBody Spit spit) {
        spitService.insert(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 编辑修改
     *
     * @param spit
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit) {
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据上级ID查询吐槽分页数据
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageList = spitService.findByParentid(parentId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 点赞,吐槽
     * @param id
     * @return
     */
    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id) {
        //判断用户是否已经点过赞了
        String userid="1314";  //后面会修改为当前登录人的id
        if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id)!=null){
            return new Result(false, StatusCode.ERROR, "您已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_"+userid+"_"+id,"1");
        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
