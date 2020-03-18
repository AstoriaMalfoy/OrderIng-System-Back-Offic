package cn.net.astoria.service;

import cn.net.astoria.domain.FoodOrder;
import cn.net.astoria.domain.TableJoinFood;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @ClassName TableService
 * @Description 用于处理和餐桌信息有关的业务逻辑
 * @Author Astoria
 * @Date 2020/3/6 15:15
 * @Version 1.0
 */
public interface TableService {
	/**
	 * 用于获取键值对形式的餐桌上菜信息
	 * @return
	 */
	Map<Integer, TreeSet<TableJoinFood>> getAllTableInfo();

	/**
	 * 用于上菜
	 * @param tid
	 * @return
	 */
	boolean serving(int tid);

	/**
	 * 用于获取某个单独餐桌的菜品信息
	 * @param tNum
	 * @return
	 */
	TreeSet<TableJoinFood> getTableInfo(int tNum);

	/**
	 * 用于删除某一桌上的某一道未上的菜品
	 * @param tid
	 * @return
	 */
	boolean delFood(int tid);

	/**
	 * 用于判断一桌是否可以结账
	 * @param tNum
	 * @return
	 */
	boolean canInvoicing(int tNum);

	/**
	 * 结账功能
	 * @param tNum
	 * @return
	 */
	boolean invoicing(int tNum);

	/**
	 * 用于获取所有菜品的订单信息
	 * @return
	 */
	List<FoodOrder> getFoodOrder();

	/**
	 * 获取某一桌的营业额
	 * @param tNum
	 * @return
	 */
	int getTurnover(int tNum);
}
