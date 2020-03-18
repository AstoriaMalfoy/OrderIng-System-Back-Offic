package cn.net.astoria.dao;

import cn.net.astoria.domain.FoodSales;

import java.util.List;

/**
 * @ClassName FoodSales
 * @Description 销量统计相关数据库表的操作
 * @Author Astoria
 * @Date 2020/3/4 16:31
 * @Version 1.0
 */
public interface FoodSalesDao {
	/**
	 * 获取一种菜品的所有月份销售记录
	 * @param fid
	 * @return
	 */
	List<FoodSales>  getSales(int fid);

	/**
	 * 根据传入的fs_Id获取该月的销售记录
	 * @param fs_Id
	 * @return
	 */
	int getValue(String fs_Id);
}
