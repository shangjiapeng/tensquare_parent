package com.tensquare.recruit.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>招聘企业实体类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-31 18:24
 */
@Entity
@Table(name = "tb_enterprise")
@Data
@ToString
public class Enterprise implements Serializable {
    @Id
    private String id; //id
    private String name; //企业名称
    private String summary; //企业简介
    private String address;//办公地址
    private String labels;//标签列表 用逗号分隔
    private String coordinate;//坐标 经度，纬度
    private String ishot;//是否热门
    private String logo;//LOGO
    private String jobcount;//职位数
    private String url;//网址
}
