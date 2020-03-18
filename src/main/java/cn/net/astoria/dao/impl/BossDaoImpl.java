package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.BossDao;
import cn.net.astoria.domain.Boss;
import cn.net.astoria.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName BossDaoImpl
 * @Description 数据库Boss表相关操作的实现逻辑
 * @Author Astoria
 * @Date 2020/3/3 15:26
 * @Version 1.0
 */
public class BossDaoImpl implements BossDao {
    /**
     * 定义JdbcTemplate工具类
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());


    @Override
    public Boss CheckUsernameAndPassword(String username, String password) {
        Boss boss = null;
        try {
            String sql = "SELECT * FROM boss WHERE b_name = ? AND b_password = ?";
            boss = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Boss>(Boss.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return boss;
    }
}
