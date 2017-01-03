package cn.blinkit.transactionManager.anno;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-02 14:50
 * @Description:
 */

public class OrdersDao {
    //注入jdbcTemplate
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 做对数据库操作的方法，不写业务操作
     */
    //少钱方法
    public void lessMoney() {
        String sql = "update account set salary=salary-? where username=?";
        jdbcTemplate.update(sql, 1000,"小王");

    }

    //多钱方法
    public void moreMoney() {
        String sql = "update account set salary=salary+? where username=?";
        jdbcTemplate.update(sql, 1000,"小马");
    }
}
