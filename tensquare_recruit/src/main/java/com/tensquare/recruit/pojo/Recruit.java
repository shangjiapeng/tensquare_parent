package com.tensquare.recruit.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>招聘表实体类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:30
 */
@Entity
@Table(name = "tb_recruit")
@Data
@ToString
public class Recruit {
    @Id
    private String id; //id
    private String jobname; //职位名称
    private String salary; //薪资范围
    private String condition; //经验要求
    private String education; //学历要求
    private String type; //任职方式
    private String address; //办公地址
    private String eid; //企业ID
    private java.util.Date createtime; //发布日期
    private String state; //状态 0:关闭 1:开启 2:推荐
    private String url; //原网址
    private String label; //标签
    private String content1; //职位描述
    private String content2; //职位要求
}
