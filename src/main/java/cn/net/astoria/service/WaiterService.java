package cn.net.astoria.service;

import cn.net.astoria.domain.Waiter;
import cn.net.astoria.domain.WaiterPer;

import java.util.List;

/**
 * @ClassName WaiterService
 * @Description 服务员相关Service接口
 * @Author Astoria
 * @Date 2020/3/9 17:48
 * @Version 1.0
 */
public interface WaiterService {
	/**
	 * 获取服务员的集合 对密码进行替换
	 * @return
	 */
	List<Waiter> getWaiterList();


	/**
	 * 用于获取服务员近一个月的订单详情
	 * @param wid
	 * @return
	 */
	List<Integer> getWaiterMonthSales(int wid);

	/**
	 * 用于获取服务员数量
	 * @return
	 */
	int getUserCount();

	/**
	 * 获取今天的销售数量
	 * @return
	 */
	int getTodayFoodCount();

	/**
	 * 获取每日员工排名
	 * @return
	 */
	List<WaiterPer> getHotWaiter();

	/**
	 * 用于获取包含密码的服务员列表
	 * @return
	 */
	List<Waiter> getWaiterListAndPassword();

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
	 * 删除相应服务员的信息
	 * @param wid
	 * @return
	 */
	boolean deleteService(int wid);

}
