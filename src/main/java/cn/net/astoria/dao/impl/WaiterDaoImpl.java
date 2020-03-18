package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.WaiterDao;
import cn.net.astoria.domain.Waiter;
import cn.net.astoria.domain.WaiterPer;
import cn.net.astoria.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName WaiterDaoImpl
 * @Description waiter数据库操作实现类
 * @Author Astoria
 * @Date 2020/3/9 17:51
 * @Version 1.0
 */
public class WaiterDaoImpl implements WaiterDao {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

	@Override
	public List<Waiter> getWaiterList() {
		List<Waiter> waiters = null;
		try {
			String sql = "SELECT * FROM waiter";
			waiters = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Waiter>(Waiter.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return waiters;
	}

	@Override
	public List<Integer> getWaiterMonthSalery(int wid) {
		List<Integer> result = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String thisMonth = simpleDateFormat.format(System.currentTimeMillis());
		StringBuilder parameter = new StringBuilder();
		parameter.append(thisMonth).append("%");
		if(wid < 10){
			parameter.append("00").append(wid);
		}else if(wid < 100){
			parameter.append("0").append(wid);
		}else{
			parameter.append(wid);
		}
		try {
			String sql = "SELECT wp_orderCount FROM waiterperformance WHERE wp_id LIKE ? ORDER BY wp_id";
			result =  jdbcTemplate.queryForList(sql,Integer.class, parameter.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getUserCount() {
		Integer result = null;
		try {
			String sql = "SELECT COUNT(*) FROM waiter";
			result = jdbcTemplate.queryForObject(sql, Integer.class, null);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<WaiterPer> getHotWaiter() {
		List<WaiterPer> waiters = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = simpleDateFormat.format(System.currentTimeMillis());
		try {
			String sql = "SELECT * FROM waiterperformance WHERE wp_id LIKE ? ORDER BY wp_orderCount DESC ";
			waiters = jdbcTemplate.query(sql, new BeanPropertyRowMapper<WaiterPer>(WaiterPer.class), date + '%');
		} catch (DataAccessException e) {

		}
		return waiters;
	}

	@Override
	public String getUserName(int userId) {
		String userName = null;
		try {
			String sql = "SELECT w_username FROM waiter WHERE w_id = ?";
			userName = jdbcTemplate.queryForObject(sql, String.class, userId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return userName;
	}

	@Override
	public boolean newWaiter(String name, String password) {
		Boolean result = false;
		int i = 0;
		try {
			String sql = "INSERT INTO waiter (w_username ,w_password) VALUES (?,?)";
			i = jdbcTemplate.update(sql, name, password);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(i == 1){
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateWaiter(int wid, String name, String password) {
		Boolean result = false;
		int update = 0;
		try {
			String sql = "UPDATE waiter SET w_username = ? , w_password = ? WHERE w_id = ?";
			update = jdbcTemplate.update(sql, name, password, wid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(update == 1){
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteWaiter(int wid) {
		Boolean result = false;
		int update = 0;
		try {
			String sql = "DELETE FROM waiter WHERE w_id = ?";
			update = jdbcTemplate.update(sql, wid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(update != 0){
			result = true;
		}
		return result;
	}

}
