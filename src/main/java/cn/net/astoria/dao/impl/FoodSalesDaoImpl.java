package cn.net.astoria.dao.impl;

import cn.net.astoria.dao.FoodSalesDao;
import cn.net.astoria.domain.FoodSales;
import cn.net.astoria.utils.DruidUtils;
import com.alexkasko.springjdbc.iterable.IterableNamedParameterJdbcOperations;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName FoodSalesDaoImpl
 * @Description 销量统计相关数据库表操作的实现
 * @Author Astoria
 * @Date 2020/3/4 16:33
 * @Version 1.0
 */
public class FoodSalesDaoImpl implements FoodSalesDao {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

	@Override
	public List<FoodSales> getSales(int fid) {
		List<FoodSales> foodSales = null;
		String parameter = null;
		if(fid < 10){
			parameter = "%00" + fid;
		}else if(fid < 100){
			parameter = "%0" + fid;
		}else{
			parameter = "%" + fid;
		}
		try {
			String sql = "SELECT * FROM foodsales WHERE fs_Id like ? ORDER BY fs_Id DESC";
			foodSales = jdbcTemplate.query(sql, new BeanPropertyRowMapper<FoodSales>(FoodSales.class), parameter);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return foodSales;
	}

	@Override
	public int getValue(String fs_Id) {
		int value = 0;
		try {
			String sql = "SELECT fs_count FROM foodsales WHERE fs_ID = ?";
			value = jdbcTemplate.queryForObject(sql, Integer.class, fs_Id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
}
