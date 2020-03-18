package cn.net.astoria.service.impl;

import cn.net.astoria.dao.FoodSalesDao;
import cn.net.astoria.dao.impl.FoodSalesDaoImpl;
import cn.net.astoria.domain.FoodSales;
import cn.net.astoria.service.FoodSalesService;
import cn.net.astoria.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName FoodSalesServiceImpl
 * @Description 销量统计相关接口的具体实现
 * @Author Astoria
 * @Date 2020/3/4 16:24
 * @Version 1.0
 */
public class FoodSalesServiceImpl implements FoodSalesService {
	private FoodSalesDao foodSalesDao = new FoodSalesDaoImpl();

	@Override
	public List<FoodSales> getSales(int fid) {
		return foodSalesDao.getSales(fid);
	}
}
