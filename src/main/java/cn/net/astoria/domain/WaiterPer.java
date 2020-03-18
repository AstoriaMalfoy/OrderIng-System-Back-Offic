package cn.net.astoria.domain;

import org.springframework.scheduling.support.SimpleTriggerContext;

/**
 * @ClassName WaiterPer
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/10 18:01
 * @Version 1.0
 */
public class WaiterPer {
	private String wp_id;
	private String userName;
	private int wp_orderCount;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WaiterPer(String wp_id, String userName, int wp_orderCount) {
		this.wp_id = wp_id;
		this.userName = userName;
		this.wp_orderCount = wp_orderCount;
	}

	public WaiterPer(String wp_id, int wp_orderCount) {
		this.wp_id = wp_id;
		this.wp_orderCount = wp_orderCount;
	}

	public WaiterPer() {
	}

	public String getWp_id() {
		return wp_id;
	}

	public void setWp_id(String wp_id) {
		this.wp_id = wp_id;
	}

	public int getWp_orderCount() {
		return wp_orderCount;
	}

	public void setWp_orderCount(int wp_orderCount) {
		this.wp_orderCount = wp_orderCount;
	}
}
