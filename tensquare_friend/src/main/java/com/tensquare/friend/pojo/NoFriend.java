package com.tensquare.friend.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * <p>非好友</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 14:00
 */
@Data
@ToString
@Entity
@Table(name="tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend {
    private String userid;   //用户ID
    private String friendid; //好友ID
}
