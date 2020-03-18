package cn.net.astoria.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FoodOrder
 * @Description 用于对应前台展示菜品的总订单信息的数据结构
 * @Author Astoria
 * @Date 2020/3/9 10:53
 * @Version 1.0
 */
public class FoodOrder {
	/**
	 *	菜品编号
	 */
	private int f_id;
	/**
	 * 菜品名称
	 */
	private String foodName;
	/**
	 * 对应的所有桌号
	 */
	private List<Integer> tableList;
	/**
	 * 对应的table表中的数据ID
	 */
	private List<Integer> tableIdList;

	public FoodOrder(int f_id, String foodName, List<Integer> tableList, List<Integer> tbaleIdList) {
		this.f_id = f_id;
		this.foodName = foodName;
		this.tableList = tableList;
		this.tableIdList = tbaleIdList;
	}

	public FoodOrder() {
		this.tableList = new ArrayList<>();
		this.tableIdList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "FoodOrder{" +
				"f_id=" + f_id +
				", foodName='" + foodName + '\'' +
				", tableList=" + tableList +
				", tbaleIdList=" + tableIdList +
				'}';
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public List<Integer> getTableList() {
		return tableList;
	}

	public void setTableList(List<Integer> tableList) {
		this.tableList = tableList;
	}

	public List<Integer> getTableIdList() {
		return tableIdList;
	}

	public void setTableIdList(List<Integer> tableIdList) {
		this.tableIdList = tableIdList;
	}
	public void tableListAdd(Integer tableNum){
		this.tableList.add(tableNum);
	}
	public void tableIdAdd(Integer tableId){
		this.tableIdList.add(tableId);
	}
}
