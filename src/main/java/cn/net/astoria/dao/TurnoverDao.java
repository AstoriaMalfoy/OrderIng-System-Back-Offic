package cn.net.astoria.dao;

import java.util.List;

/**
 * @ClassName TurnoverDao
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/10 16:42
 * @Version 1.0
 */
public interface TurnoverDao {

	/**
	 * 从数据库中获取今日的营业额
	 * @return
	 */
	int getMoney();

	/**
	 * 获取最近15天的营业额
	 * @return
	 */
	List<Integer> getRecently();

	/**
	 * 用于添加营业额
	 * @param tNum
	 * @param turnover
	 */
	void addTurnover( int turnover);
}
