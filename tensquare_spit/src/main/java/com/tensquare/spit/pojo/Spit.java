package com.tensquare.spit.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>吐槽内容实体类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-08 16:45
 */
@Data
@ToString
@Document(collection = "spit")
public class Spit implements Serializable {
    @Id
    private String _id;  //主键youxiao
    private String content; //内容
    private Date publishtime; //发布时间
    private String userid;  //用户id
    private String nickname; //用户昵称
    private Integer visits; //查看次数
    private Integer thumbup; //点赞次数
    private Integer share;  //分享次数
    private Integer comment; //评论次数
    private String state;    //状态
    private String parentid;  //上级id,如果为0表示文章的顶级吐槽
}
