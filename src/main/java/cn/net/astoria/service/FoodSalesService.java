package cn.net.astoria.service;

import cn.net.astoria.domain.FoodSales;

import java.util.List;

/**
 * @ClassName FoodSalesService
 * @Description 用于销量统计的相关接口
 * @Author Astoria
 * @Date 2020/3/4 16:21
 * @Version 1.0
 */
public interface FoodSalesService {
	List<FoodSales> getSales(int fid);
}
