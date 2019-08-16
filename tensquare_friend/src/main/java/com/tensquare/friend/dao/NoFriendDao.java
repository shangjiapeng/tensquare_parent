package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 14:06
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String>, JpaSpecificationExecutor<NoFriend> {
}
