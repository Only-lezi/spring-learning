package cn.blinkit.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 22:13
 * @Description:
 */

public class UserDao {

    //得到JdbcTemplate对象
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //添加操作
    public void add() {
        //创建jdbcTemplate对象
        String sql = "INSERT INTO user values(null,?,?)";
        int rows = jdbcTemplate.update(sql, "张三丰", "zhangsanfeng");
        System.out.println(rows);
    }
}
