package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.common.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:36
 */
@Service
public class RecruitService {
    @Resource
    private RecruitDao recruitDao;
    @Resource
    private IdWorker idWorker;

    /**
     * 根据状态查询
     * @param state
     * @return
     */
    public List<Recruit> recommendList(String state){
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc(state);
    }


    /**
     * 查询最新职位表
     * 查询状态不为0并以创建日期降序排序，查询前12条记录
     */
    public List<Recruit> newList (String state){
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc(state);
    }
}
