package com.tensquare.article_crawler.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>文章频道实体类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-23 18:19
 */
@Data
@ToString
@Entity
@Table(name = "tb_channel")
public class Channel implements Serializable {

    @Id
    private String id;  //id
    private String name; ////频道名称
    private String state; //状态
}