package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * problem数据访问接口
 *
 * @author shangjiapeng
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    /**
     * 根据标签id查询最新回答列表
     * 按回复时间降序排序
     *
     * @param labelId
     * @return
     */
    @Query("select p from Problem p where id in(select problemid from Pl where labelid=?1 ) order by replyname desc")
    public Page<Problem> findNewReplyListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签ID查询热门问题列表
     * 按回复数降序排序
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in(select problemid from Pl where labelid=?1) order by reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);


    /**
     * 根据标签ID查询等待回答列表
     * 查询回复数为0的,按照问题创建的时间进行倒序排列
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid =?1) and reply = 0 order by createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);

}