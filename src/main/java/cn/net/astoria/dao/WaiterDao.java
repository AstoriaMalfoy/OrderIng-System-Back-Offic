package cn.net.astoria.dao;

import cn.net.astoria.domain.Waiter;
import cn.net.astoria.domain.WaiterPer;

import java.util.List;

/**
 * @ClassName WaiterDao
 * @Description waiter数据库操作接口
 * @Author Astoria
 * @Date 2020/3/9 17:50
 * @Version 1.0
 */
public interface WaiterDao {
	/**
	 * 获取所有的服务员信息
	 * @return
	 */
	List<Waiter> getWaiterList();

	/**
	 * 在数据库中获取服务员近一个月的销量信息
	 * @param wid
	 * @return
	 */
	List<Integer> getWaiterMonthSalery(int wid);

	/**
	 * 用于获取服务员数量
	 * @return
	 */
	int getUserCount();

	/**
	 * 用于获取每日员工排行
	 * @return
	 */
	List<WaiterPer> getHotWaiter();

	/**
	 * 用于获取用户名
	 * @param userId
	 * @return
	 */
	String getUserName(int userId);

	/**
	 * 用于创建新的服务员
	 * @param name
	 * @param password
	 * @return
	 */
	boolean newWaiter(String name, String password);

	/**
	 * 用于更新服务员信息
	 * @param wid
	 * @param name
	 * @param password
	 * @return
	 */
	boolean updateWaiter(int wid, String name, String password);

	/**
	 * 用于删除摸一个服务员的信息
	 * @param wid
	 * @return
	 */
	boolean deleteWaiter(int wid);

}
