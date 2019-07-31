package com.tensquare.base.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>标签实体类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 16:47
 */
@Entity
@Table(name = "tb_label")
@Data
@ToString
public class Label {
    @Id
    private String id; //
    private String labelname; //标签名称
    private String state; // 状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐
    




}
