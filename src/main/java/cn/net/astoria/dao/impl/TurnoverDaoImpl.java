package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.TurnoverDao;
import cn.net.astoria.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName TurnoverDaoImpl
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/10 16:42
 * @Version 1.0
 */
public class TurnoverDaoImpl implements TurnoverDao {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

	@Override
	public int getMoney() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = simpleDateFormat.format(System.currentTimeMillis());
		String sql = "SELECT revenue FROM turnover WHERE date = ?";
		Integer result = 0;
		try {
			result = jdbcTemplate.queryForObject(sql, Integer.class, date);
		} catch (DataAccessException e) {
			try {
				sql = "INSERT INTO turnover (date,revenue) VALUES (?,0)";
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
				jdbcTemplate.update(sql,date);
			} catch (DataAccessException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Integer> getRecently() {
		List<Integer> recentlyTurnover = null;
		try {
			String sql = "SELECT revenue FROM turnover ORDER BY date DESC LIMIT ?,?";
			recentlyTurnover =  jdbcTemplate.queryForList(sql,Integer.class, 0,15);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return recentlyTurnover;
	}

	@Override
	public void addTurnover( int turnover) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date =  simpleDateFormat.format(System.currentTimeMillis());
		try {
			String sql = "UPDATE turnover SET revenue = revenue + ? WHERE date = ?";
			jdbcTemplate.update(sql,turnover,date);
		} catch (DataAccessException e) {
			try {
				String sql = "INSERT INTO turnover (date,revenue) VALUES (?,0)";
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
				jdbcTemplate.update(sql,date);
				sql = "UPDATE turnover SET revenue = revenue + ? WHERE date = ?";
				jdbcTemplate.update(sql,turnover,date);
			} catch (DataAccessException ex) {
				ex.printStackTrace();
			}

		}
	}
}
