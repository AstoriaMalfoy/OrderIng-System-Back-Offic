package cn.net.astoria.dao;

import cn.net.astoria.domain.TableJoinFood;

import java.util.List;

/**
 * @ClassName tableDao
 * @Description 和上菜表相关的数据库DAO
 * @Author Astoria
 * @Date 2020/3/4 14:18
 * @Version 1.0
 */
public interface TableDao {
	/**
	 * 获取一种类型的菜品目前有几个订单
	 * @param fid
	 * @return
	 */
	int getFoodCount(int fid);

	/**
	 * 用于获取所有餐桌的点菜信息
	 * @return
	 */
	List<TableJoinFood> getAllTableInfo();

	/**
	 * 用于上菜的数据库Dao操作
	 * @param tid
	 * @return
	 */
	boolean serving(int tid);

	/**
	 * 获取某一个单独餐桌的菜品信息
	 * @param tNum
	 * @return
	 */
	List<TableJoinFood> getTableInfo(int tNum);

	/**
	 * 用于删除某一桌上的莫一道菜
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
	 * 处理结账业务
	 * @param tNum
	 * @return
	 */
	boolean invoicing(int tNum);

	/**
	 * 获取菜品订单列表
	 * @return
	 */
	List<TableJoinFood> getFoodOrder();

	/**
	 * 用于获取某一桌的总额
	 * @param tNum
	 * @return
	 */
	int getTurnover(int tNum);
}
