package cn.net.astoria.dao;

/**
 * @ClassName WaiterPerformance
 * @Description 数据库WaiterPerformance处理接口
 * @Author Astoria
 * @Date 2020/3/9 18:19
 * @Version 1.0
 */
public interface WaiterPerformanceDao {

	/**
	 * 获取服务员当天的销量数据
	 * @param w_id
	 * @param dayFoemat
	 * @return
	 */
	int getWaiterTodayCount(int w_id, String dayFoemat);

	/**
	 * 获取当月的销量数据
	 * @param w_id
	 * @param monthFormate
	 * @return
	 */
	int getWaiterMonthCount(int w_id, String monthFormate);

	/**
	 * 获取某一天的销售总量
	 * @param formatDate
	 * @return
	 */
	int getDayCount(String formatDate);

	/**
	 * 用于删除摸一个服务员的绩效信息
	 * @param wid
	 * @return
	 */
	boolean deleteWaite(int wid);
}
