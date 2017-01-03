package cn.blinkit.jdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 18:39
 * @Description:
 */

public class MyRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int num) throws SQLException {
        //1.从结果集里面把数据得到
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");

        //2.把得到数据封装到对象里面
        User user = new User(id, username, password);
        return user;
    }
}
