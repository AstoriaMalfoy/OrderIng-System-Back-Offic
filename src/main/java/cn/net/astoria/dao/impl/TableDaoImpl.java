package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.TableDao;
import cn.net.astoria.domain.TableJoinFood;
import cn.net.astoria.utils.DruidUtils;
import org.codehaus.jackson.map.ser.std.StdArraySerializers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName TableDaoImpl
 * @Description 数据库table表的操作实现类
 * @Author Astoria
 * @Date 2020/3/4 14:19
 * @Version 1.0
 */
public class TableDaoImpl implements TableDao {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());
	@Override
	public int getFoodCount(int fid) {
		Integer foodCount = 0;
		try {
			String sql = "SELECT count(*) FROM `table` WHERE f_id = ?";
			foodCount = jdbcTemplate.queryForObject(sql, Integer.class, fid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return foodCount;
	}

	@Override
	public List<TableJoinFood> getAllTableInfo() {
		List<TableJoinFood> tableJoinFoods = null;
		try {
			String sql = "SELECT `table`.`t_id`,`table`.`f_id`,`table`.`w_id`,`table`.`t_num`,`table`.`t_state`,food.`f_name`,food.`f_price` FROM `table` LEFT JOIN food ON `table`.`f_id` = food.`f_id` ORDER BY `table`.`t_num`,`table`.`f_id`;";
			tableJoinFoods = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TableJoinFood>(TableJoinFood.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return tableJoinFoods;
	}

	@Override
	public boolean serving(int tid) {
		int update = -1;
		try {
			String sql = "UPDATE `table` SET t_state = 'true' WHERE t_id = ? ";
			update = jdbcTemplate.update(sql, tid);
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
	public List<TableJoinFood> getTableInfo(int tNum) {
		List<TableJoinFood> foods = null;
		try {
			String sql = "SELECT `table`.`t_id`,`table`.`f_id`,`table`.`w_id`,`table`.`t_num`,`table`.`t_state`,food.`f_name`,food.`f_price` FROM `table` LEFT JOIN food ON `table`.`f_id` = food.`f_id`  WHERE `table`.`t_num` = ? ORDER BY `table`.`t_num`,`table`.`f_id`;";
			foods = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TableJoinFood>(TableJoinFood.class), tNum);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return foods;
	}

	@Override
	public boolean delFood(int tid) {
		int result = -1;
		try {
			String sql = "DELETE FROM `table` WHERE t_id = ?";
			result = jdbcTemplate.update(sql, tid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		if(result ==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean canInvoicing(int tNum) {
		List<TableJoinFood> query = null;
		try {
			String sql = "SELECT * FROM `table` WHERE t_state = 'false' AND t_num = ?";
			query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TableJoinFood>(TableJoinFood.class), tNum);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(query == null || query.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean invoicing(int tNum) {
		int result = -1;
		try {
			String sql =  "DELETE FROM `table` WHERE t_num = ?";
			jdbcTemplate.update(sql,tNum);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		try {
			String sql = "SELECT count(*) FROM `table` WHERE t_num = ?";
			result = jdbcTemplate.queryForObject(sql, Integer.class, tNum);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(result == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<TableJoinFood> getFoodOrder() {
		List<TableJoinFood> foodOrders = null;
		try {
			String sql = "SELECT `table`.`t_id`,`table`.`f_id`,`table`.`t_num`,`food`.`f_name` FROM `table`  INNER JOIN `food` ON `table`.`f_id` = `food`.`f_id` WHERE `table`.`t_state` = 'false' ORDER BY `table`.`f_id`;";
			foodOrders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TableJoinFood>(TableJoinFood.class), null);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(foodOrders == null){
			return null;
		}else{
			return foodOrders;
		}

	}

	@Override
	public int getTurnover(int tNum) {
		int turnover = 0;
		try {
			String sql = "SELECT SUM(`food`.`f_price`) FROM `table` LEFT JOIN food ON `table`.`f_id` = food.`f_id`  WHERE `table`.`t_num` = ? ORDER BY `table`.`t_num`,`table`.`f_id`;";
			turnover = jdbcTemplate.queryForObject(sql, Integer.class, tNum);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return turnover;
	}
}
