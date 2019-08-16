package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.Admin;
import org.springframework.data.jpa.repository.Query;

/**
 * admin数据访问接口
 *
 * @author Administrator
 */
public interface AdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

    /**
     * 根据登录名称查询
     * @param loginname
     * @return
     */
    @Query("select admin from Admin admin where admin.loginname=? 1")
    public Admin findByLoginname(String loginname);

}
