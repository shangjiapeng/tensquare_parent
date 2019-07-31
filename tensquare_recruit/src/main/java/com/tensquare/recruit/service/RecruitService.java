package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
