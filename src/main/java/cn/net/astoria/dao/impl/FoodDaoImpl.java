package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.FoodDao;
import cn.net.astoria.domain.Food;
import cn.net.astoria.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.remoting.support.RemoteInvocationTraceInterceptor;
import sun.text.resources.in.FormatData_in_ID;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName FoodDaoImpl
 * @Description 数据库Food表的操作实现
 * @Author Astoria
 * @Date 2020/3/3 20:32
 * @Version 1.0
 */
public class FoodDaoImpl  implements FoodDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

    @Override
    public List<Food> getAll() {
        List<Food> foods = null;
        try {
            String sql = "SELECT * FROM food";
            foods = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Food>(Food.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return foods;
    }


    @Override
    public List<Food> getListPaging(int start, int size) {
        List<Food> foods = null;
        try {
            String sql = "SELECT * FROM food LIMIT ? , ?";
            foods = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Food>(Food.class),start,size);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public int getListCount() {
        String sql = "SELECT count(*) FROM food";
        Integer listCount = jdbcTemplate.queryForObject(sql,Integer.class);
        return listCount;
    }

    @Override
    public Food getFoodById(int fid) {
        Food food = null;
        try {
            String sql = "SELECT * FROM food WHERE f_id = ?";
            food = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Food>(Food.class), fid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return food;
    }

    @Override
    public boolean updateFood(Food food) {
        System.out.println(food);
        String sql = "UPDATE food SET f_name = ? , f_price = ? WHERE f_id = ?";
        int update = jdbcTemplate.update(sql, food.getF_name(), food.getF_price(), food.getF_id());
        if(update == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean insertFood(Food food) {
        int update = 0;
        try {
            String sql = "INSERT INTO food (f_name,f_price) VALUES (?,?)";
            update = jdbcTemplate.update(sql, food.getF_name(), food.getF_price());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if(update == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Food getFoodByName(String f_name) {
        Food food = null;
        try {
            String sql = "SELECT * FROM food WHERE f_name = ?";
            food = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Food>(Food.class), f_name);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return food;
    }

    @Override
    public boolean deleteFood(int fid) {
        int update = 0;
        try {
            String sql = "DELETE FROM food WHERE f_id = ?";
            update = jdbcTemplate.update(sql, fid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if(update == 1){
            return true;
        }else{
            return false;
        }
    }


}
