package cn.net.astoria.service.impl;

import cn.net.astoria.dao.TableDao;
import cn.net.astoria.dao.impl.TableDaoImpl;
import cn.net.astoria.domain.FoodOrder;
import cn.net.astoria.domain.TableJoinFood;
import cn.net.astoria.service.TableService;

import java.util.*;

/**
 * @ClassName TableServiceImpl
 * @Description 处理餐桌和餐桌有关信息的实现类
 * @Author Astoria
 * @Date 2020/3/6 15:16
 * @Version 1.0
 */
public class  TableServiceImpl implements TableService {

	private TableDao tableDao = new TableDaoImpl();


	/**
	 * 用于获取所有桌子的上菜信息
	 * @return
	 */
	@Override
	public Map<Integer, TreeSet<TableJoinFood>> getAllTableInfo() {
		// table列表中的所有数据
		List<TableJoinFood> allTableInfo = tableDao.getAllTableInfo();
		// 传送给客户端的table格式化数据 Integer存储桌号 从小到大排序 值为TableJoinFood 按照f_id进行排序
		Map<Integer,TreeSet<TableJoinFood>> tableMap = new TreeMap<>();
		int nowTableNumber = -1;
		for (TableJoinFood tableList : allTableInfo) {
			if(tableList.getT_num()!=nowTableNumber){
//				新添加桌子
				try {
					nowTableNumber = tableList.getT_num();
					tableMap.put(nowTableNumber, new TreeSet<>());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			将现在的记录添加到tableMap中
			tableMap.get(nowTableNumber).add(tableList);
		}
		return tableMap;
	}

	@Override
	public boolean serving(int tid) {
		return tableDao.serving(tid);
	}

	@Override
	public TreeSet<TableJoinFood> getTableInfo(int tNum) {
		List<TableJoinFood> tableInfo = tableDao.getTableInfo(tNum);
		if(tableInfo!=null){
			TreeSet<TableJoinFood> foods = new TreeSet<>();
			for (TableJoinFood tableJoinFood : tableInfo) {
				foods.add(tableJoinFood);
			}
			return foods;
		}else{
			return null;
		}
	}

	@Override
	public boolean delFood(int tid) {
		return tableDao.delFood(tid);
	}

	@Override
	public boolean canInvoicing(int tNum) {
		return tableDao.canInvoicing(tNum);
	}

	@Override
	public boolean invoicing(int tNum) {
		return tableDao.invoicing(tNum);
	}

	@Override
	public List<FoodOrder> getFoodOrder() {
		List<TableJoinFood> foodsOrderList = null;
		foodsOrderList = tableDao.getFoodOrder();
		if(foodsOrderList == null){
			return null;
		}else{
			List<FoodOrder> foodOrders = new ArrayList<>();
			int foodIdFlag = -1;
			for (TableJoinFood tableJoinFood : foodsOrderList) {
				if(tableJoinFood.getF_id() != foodIdFlag){
					FoodOrder foodOrder = new FoodOrder();
					foodOrder.setF_id(tableJoinFood.getF_id());
					foodOrder.setFoodName(tableJoinFood.getF_name());
					foodOrder.tableIdAdd(tableJoinFood.getT_id());
					foodOrder.tableListAdd(tableJoinFood.getT_num());
					foodOrders.add(foodOrder);
					foodIdFlag = tableJoinFood.getF_id();
				}else{
					foodOrders.get(foodOrders.size()-1).tableIdAdd(tableJoinFood.getT_id());
					foodOrders.get(foodOrders.size()-1).tableListAdd(tableJoinFood.getT_num());
				}
			}
			return foodOrders;
		}
	}

	@Override
	public int getTurnover(int tNum) {
		return tableDao.getTurnover(tNum);
	}
}
