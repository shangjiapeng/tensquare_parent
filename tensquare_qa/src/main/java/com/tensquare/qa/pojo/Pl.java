package com.tensquare.qa.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p></p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-01 17:16
 */
@Entity
@Data
@ToString
@Table(name = "tb_pl")
public class Pl implements Serializable {

    @Id
    private String problemid;
    @Id
    private String labelid;
}


