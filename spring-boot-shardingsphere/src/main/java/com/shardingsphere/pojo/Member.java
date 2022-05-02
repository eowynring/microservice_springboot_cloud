package com.shardingsphere.pojo;

import lombok.Data;

/**
 * @author: GuoFei
 * @date: 2022-05-02 14:22
 */
//@TableName("member")
@Data
public class Member {

    /**
     * `id` int(11) NOT NULL,
     *   `name` varchar(255) DEFAULT NULL,
     */

   // @TableId
    private  Integer id;

    private String name;
}

