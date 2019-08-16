package com.tensquare.friend.pojo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>好友</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 14:00
 */
@Entity
@Data
@ToString
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private String userid;   //用户ID
    @Id
    private String friendid; //好友ID
    private String mutuallike;  //是否互相喜欢(1,0)


}
