package cn.net.astoria.service.impl;

import cn.net.astoria.dao.WaiterDao;
import cn.net.astoria.dao.WaiterPerformanceDao;
import cn.net.astoria.dao.impl.WaiterDaoImpl;
import cn.net.astoria.dao.impl.WaiterPerformamceDaoImpl;
import cn.net.astoria.domain.Waiter;
import cn.net.astoria.domain.WaiterPer;
import cn.net.astoria.service.WaiterService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WaiterServiceImpl
 * @Description 服务员Service实现类
 * @Author Astoria
 * @Date 2020/3/9 17:49
 * @Version 1.0
 */
public class WaiterServiceImpl implements WaiterService {

	private WaiterDao waiterDao = new WaiterDaoImpl();
	private WaiterPerformanceDao waiterPerformanceDao = new WaiterPerformamceDaoImpl();

	@Override
	public List<Waiter> getWaiterList() {
		List<Waiter> waiters = new ArrayList<>();
		waiters = waiterDao.getWaiterList();
		if(waiters == null){
			return null;
		}else{
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
			String dayFoemat = dateFormat1.format(System.currentTimeMillis());
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMM");
			String monthFormate = dateFormat2.format(System.currentTimeMillis());
			for (Waiter waiter : waiters) {
				try {
					waiter.setTodayOrderCount(waiterPerformanceDao.getWaiterTodayCount(waiter.getW_id(),dayFoemat));
					waiter.setMonthOrderCount(waiterPerformanceDao.getWaiterMonthCount(waiter.getW_id(),monthFormate));
				} catch (Exception e) {
					e.printStackTrace();
				}
				waiter.setW_password("******");
			}
			return waiters;
		}
	}

	@Override
	public List<Integer> getWaiterMonthSales(int wid) {
		return waiterDao.getWaiterMonthSalery(wid);
	}

	@Override
	public int getUserCount() {
		return waiterDao.getUserCount();
	}

	@Override
	public int getTodayFoodCount() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String formatDate = simpleDateFormat.format(System.currentTimeMillis());
		return waiterPerformanceDao.getDayCount(formatDate);
	}

	@Override
	public List<WaiterPer> getHotWaiter() {
		List<WaiterPer> hotWaiter = waiterDao.getHotWaiter();
		if(hotWaiter != null){
			for (WaiterPer waiterPer : hotWaiter) {
				int userId = Integer.parseInt(waiterPer.getWp_id().substring(8));
				waiterPer.setUserName(waiterDao.getUserName(userId));
			}
		}
		return hotWaiter;
	}

	@Override
	public List<Waiter> getWaiterListAndPassword() {
		List<Waiter> waiters = waiterDao.getWaiterList();
		return waiters;
	}

	@Override
	public boolean newWaiter(String name, String password) {
		return waiterDao.newWaiter(name,password);
	}

	@Override
	public boolean updateWaiter(int wid, String name, String password) {
		return waiterDao.updateWaiter(wid,name,password);
	}

	@Override
	public boolean deleteService(int wid) {
		if(waiterDao.deleteWaiter(wid)){
			waiterPerformanceDao.deleteWaite(wid);
			return true;
		}else{
			return false;
		}
	}
}
