package com.tensquare.spit.service;

import com.mongodb.QueryBuilder;
import com.tensquare.common.util.IdWorker;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-08 16:54
 */
@Service
public class SpitService {

    @Resource
    private SpitDao spitDao;
    @Resource
    private IdWorker idWorker;
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 查询全部
     *
     * @return
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Spit findById(String id) {
        Optional<Spit> optional = spitDao.findById(id);
        Spit spit = optional.get();
        return spit;
    }

    /**
     * 发布吐槽(或吐槽评论)
     *
     * @param spit
     */
    public void insert(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        //设置创建通知此消息的时间
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//        String publish_time = dateFormat.format(new Date());
        spit.setPublishtime(new Date()); //发布日期
        spit.setVisits(0);   //浏览量
        spit.setShare(0);    //分享数
        spit.setComment(0);  //评论数
        spit.setThumbup(0);  //点赞数
        spit.setState("1");  //状态
        if (spit.getParentid()!=null&&!"".equals(spit.getParentid())){
            //如果存在上级id,评论,创建一个查询条件
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);  //inc() 列值增长的方法--->增加评论
            //修改满足查询条件的第一条 (这个方法执行的原理是,先查询再修改)
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 编辑修改
     *
     * @param spit
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }


    /**
     * 根据上级ID查询吐槽列表 * @param parentid
     *
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentid, pageRequest);
    }

    /**
     * 点赞,吐槽
     * 此方法虽然可以实现功能,但是代码执行的效率不高,没必要每次都把所有的数据查出来
     * 改用MongoTemplate
     *
     * @param id
     */
//    public void updateThumbup(String id) {
//        Spit spit = spitDao.findById(id).get();
//        spit.setThumbup(spit.getThumbup() + 1);
//        spitDao.save(spit);
//    }
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);  //列值增长的方法-->增加点赞数,增加浏览量和增加分享数的代码与此类似
        mongoTemplate.updateFirst(query,update,"spit");
    }


}
