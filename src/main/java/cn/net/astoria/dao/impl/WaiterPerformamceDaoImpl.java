package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.WaiterPerformanceDao;
import cn.net.astoria.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @ClassName WaiterPerformamceImpl
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/9 18:20
 * @Version 1.0
 */
public class WaiterPerformamceDaoImpl implements WaiterPerformanceDao {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

	@Override
	public int getWaiterTodayCount(int w_id, String dayFoemat) {
		StringBuilder stringBuilder = new StringBuilder();
		if(w_id < 10){
			stringBuilder.append(dayFoemat).append("00").append(w_id);
		}else if(w_id < 100){
			stringBuilder.append(dayFoemat).append("0").append(w_id);
		}else{
			stringBuilder.append(dayFoemat).append(w_id);
		}
		String wp_id = stringBuilder.toString();
		int result = -1;
		try {
			String sql = "SELECT wp_orderCount FROM waiterperformance WHERE wp_id = ?";
			result = jdbcTemplate.queryForObject(sql, Integer.class, wp_id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getWaiterMonthCount(int w_id, String monthFormate) {
		StringBuilder stringBuilder = new StringBuilder();
		if(w_id < 10){
			stringBuilder.append(monthFormate).append("%00").append(w_id);
		}else if(w_id < 100){
			stringBuilder.append(monthFormate).append("%0").append(w_id);
		}else{
			stringBuilder.append(monthFormate).append("%").append(w_id);
		}
		String wp_id = stringBuilder.toString();
		int result = -1;
		try {
			String sql = "SELECT SUM(wp_orderCount) FROM waiterperformance WHERE wp_id LIKE ? ORDER BY wp_id;";
			result = jdbcTemplate.queryForObject(sql, Integer.class, wp_id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getDayCount(String formatDate) {
		Integer result = 0;
		try {
			String sql = "SELECT SUM(wp_orderCount) FROM waiterperformance WHERE wp_id LIKE ?";
			result = jdbcTemplate.queryForObject(sql, Integer.class, formatDate + "%");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteWaite(int wid) {
		Boolean result = false;
		int i = 0;
		String sql = "DELETE FROM waiterperformance WHERE wp_id LIKE ?";
		StringBuilder parameter = new StringBuilder();
		if(wid < 10){
			parameter.append("%00").append(wid);
		}else if(wid < 100){
			parameter.append("%0").append(wid);
		}else{
			parameter.append("%").append(wid);
		}
		i = jdbcTemplate.update(sql,parameter.toString());
		if(i!=0){
			result = true;
		}
		return result;
	}
}
