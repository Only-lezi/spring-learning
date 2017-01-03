package cn.blinkit.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 21:55
 * @Description:
 */

public class PrimitiveConf {
    // c3p0 连接池的原始做法
    @Test
    public void testC3P0() throws PropertyVetoException {
        //  c3p0连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUser("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //创建sql语句
        String sql = "DELETE FROM user where id=?";
        int rows = jdbcTemplate.update(sql, 1);
        System.out.println(rows);
    }
}
