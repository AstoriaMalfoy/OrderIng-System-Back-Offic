package cn.net.astoria.service;

import java.util.List;

/**
 * @ClassName turnoverService
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/10 16:41
 * @Version 1.0
 */
public interface TurnoverService {

	/**
	 * 获取今天的营业额
	 * @return
	 */
	int getMoney();

	/**
	 * 获取最近半个月的营业额
	 * @return
	 */
	List<Integer> getRecently();

	/**
	 * 添加营业额
	 * @param tNum
	 * @param turnover
	 */
	void addTurnover( int turnover);
}
