package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:34
 */
public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {

    /**
     * 查询最新职位列表(按创建日期降序排序)
     * 查询状态为2并以创建日期降序排序，查询前4条记录
     * @return
     */
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);


    /**
     * 查询最新职位表
     * 查询状态不为0并以创建日期降序排序，查询前12条记录
     */
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
