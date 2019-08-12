package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-08 16:52
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * 根据上级ID查询吐槽列表(分页) * @param parentid
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
