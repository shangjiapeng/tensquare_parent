package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-16 14:09
 */
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Resource
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid, String friendid) {
        //判断如果用户已经添加了这个好友，则不进行任何操作,返回0
        if (friendDao.selectCount(userid, friendid) > 0) {
            return 0;
        }
        //向喜欢表中添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setMutuallike("0");  //这时还不是互相喜欢
        friendDao.save(friend);
        //判断对方是否喜欢你，如果喜欢，将islike设置为1 (参数互换位置)
        if (friendDao.selectCount(friendid, userid) > 0) {
            friendDao.updateLike(userid, friendid, "1");
            friendDao.updateLike(friendid, userid, "1");
            userClient.incFollowcount(userid,1);//增加自己的关注数
            userClient.incFanscount(friendid,1);//增加对方的粉丝数
        }
        return 1;
    }

    /**
     * 向不喜欢列表中添加记录 * @param userid
     * @param friendid
     */
    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }

    /**
     * 删除好友
     * @param userid
     * @param friendid */
    @Transactional
    public void deleteFriend(String userid,String friendid){
        friendDao.deleteFriend(userid,friendid);
        friendDao.updateLike(friendid,userid,"0");
        this.addNoFriend(userid,friendid);//向不喜欢表中添加记录
    }
}
