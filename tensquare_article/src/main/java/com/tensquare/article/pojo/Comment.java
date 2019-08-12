package com.tensquare.article.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * <p>文章评论(mongoDB)</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-09 11:14
 */
@Data
@ToString
//@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    private String articleid;
    private String content;
    private String userid;
    private String parentid;
    private Date publishdate;

}
