package com.shardingsphere.dao;

import com.shardingsphere.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: GuoFei
 * @date: 2022-05-02 15:57
 */

@Service
public class MemberDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Member> getList() {
        String sql = "select id, name from member ";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Member>(
                Member.class));
    }
}
