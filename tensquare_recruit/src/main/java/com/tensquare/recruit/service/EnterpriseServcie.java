package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:35
 */
@Service
public class EnterpriseServcie {
    @Resource
    private EnterpriseDao enterpriseDao;
    @Resource
    private IdWorker idWorker;

    /**
     * 热门企业列表 * @return
     */
    public List<Enterprise> findByIshot() {
        return enterpriseDao.findByIshot("1");
    }


}

