package cn.net.astoria.service.impl;

import cn.net.astoria.dao.TurnoverDao;
import cn.net.astoria.dao.impl.TurnoverDaoImpl;
import cn.net.astoria.service.TurnoverService;

import java.util.List;

/**
 * @ClassName turnoverServiceImpl
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/10 16:41
 * @Version 1.0
 */
public class TurnoverServiceImpl implements TurnoverService {

	private TurnoverDao turnoverDao = new TurnoverDaoImpl();

	@Override
	public int getMoney() {
		return turnoverDao.getMoney();
	}

	@Override
	public List<Integer> getRecently() {
		return turnoverDao.getRecently();
	}

	@Override
	public void addTurnover( int turnover) {
		turnoverDao.addTurnover(turnover);
	}
}
