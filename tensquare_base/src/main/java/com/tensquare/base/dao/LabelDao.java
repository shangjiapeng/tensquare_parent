package com.tensquare.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.tensquare.base.pojo.Label;

/**
 * <p>标签数据访问接口</p>
 * JpaRepository提供了基本的增删改查
 * JpaSpecificationExecutor用于做复杂的条件查询
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 16:59
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
