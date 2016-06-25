package dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by TangJiong on 2015/12/30.
 * base dao class to hold JdbcTemplate
 */
public class DaoBase {

    // 通过spring注入的属性
    // 注意必须提供public的set方法，不然spring没法注入
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
