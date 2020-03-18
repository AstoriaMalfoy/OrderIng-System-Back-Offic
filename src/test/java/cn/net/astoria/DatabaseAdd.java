package cn.net.astoria;

import cn.net.astoria.dao.WaiterPerformanceDao;
import cn.net.astoria.dao.impl.WaiterDaoImpl;
import cn.net.astoria.dao.impl.WaiterPerformamceDaoImpl;
import cn.net.astoria.service.TableService;
import cn.net.astoria.service.WaiterService;
import cn.net.astoria.service.impl.TableServiceImpl;
import cn.net.astoria.service.impl.WaiterServiceImpl;
import cn.net.astoria.utils.DruidUtils;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import com.sun.javafx.css.converters.PaintConverter;
import org.junit.Test;
import org.springframework.beans.factory.wiring.BeanWiringInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Random;

/**
 * @ClassName DatabaseAdd
 * @Description 动态向数据库中随机添加测试数据
 * @Author Astoria
 * @Date 2020/3/4 11:09
 * @Version 1.0
 */
public class DatabaseAdd {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());


	@Test
	public void test01(){
		for(int i=2;i<=3;++i){
			for(int j=1;j<=19;++j){
				String key = getString("2020", i, j);
				int value = getRandom();
				String sql = "INSERT INTO foodsales (fs_Id,fs_count) VALUES (?,?)";
				int update = jdbcTemplate.update(sql, key, value);
				System.out.println("add successful");
			}
		}
	}

	public int getRandom(){
		Random random = new Random();
		return random.nextInt(100);
	}
	public String getString(String year,int month,int id){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(year);
		if(month<10){
			stringBuilder.append("0").append(month);
		}else{
			stringBuilder.append(month);
		}
		if(id<10){
			stringBuilder.append("00").append(id);
		}else if(id < 100){
			stringBuilder.append("0").append(id);
		}else{
			stringBuilder.append(id);
		}
		return stringBuilder.toString();
	}
	public String getString(String year,int month,int day,int id){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(year);
		if(month<10){
			stringBuilder.append('0').append(month);
		}else{
			stringBuilder.append(month);
		}
		if(day < 10){
			stringBuilder.append('0').append(day);
		}else{
			stringBuilder.append(day);
		}
		if(id < 10){
			stringBuilder.append("00").append(id);
		}else if(id < 100){
			stringBuilder.append("0").append(id);
		}else {
			stringBuilder.append(id);
		}
		return stringBuilder.toString();
	}

	@Test
	public void test02(){
		Random random = new Random();
		for (int i=1;i<10;++i){
//			i 代表桌号
			int foodCount = random.nextInt(20);
			while(foodCount == 0){
				foodCount = random.nextInt(10);
			}
			for(int j=0;j<foodCount;++j){
//				随机菜品
				int foodId = random.nextInt(18);
				while(foodId == 0){
					foodId = random.nextInt(18);
				}
//				随机服务员
				int waiterId = random.nextInt(5);
				while(waiterId == 0){
					waiterId = random.nextInt(5);
				}
//				随机状态
				boolean state = random.nextBoolean();
				String stateStr = null;
				if(state){
					stateStr = "true";
				}else{
					stateStr = "false";
				}
				String sql = "INSERT INTO `table` (f_id,w_id,t_num,t_state) VALUES (?,?,?,?)";
				System.out.println(foodId + "    " + waiterId + "    " + stateStr + "    " + i);
				jdbcTemplate.update(sql,foodId,waiterId,i,stateStr);
			}
		}
	}

	@Test
	public void test03(){
		TableService tableService = new TableServiceImpl();
		System.out.println(tableService.getAllTableInfo());
	}

	@Test
	public void test04(){
		Random random = new Random();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());
		for(int i=1;i<=12;++i){
			for(int j=1;j<=30;++j){
				for(int k = 1;k<=5;++k){
					String sql = "INSERT INTO waiterperformance (wp_id,wp_orderCount) VALUES (?,?)";
					int count = random.nextInt(50);
					String id = getString("2020",i,j,k);
					System.out.println(id + "     " + count);
					jdbcTemplate.update(sql,id,count);
				}
			}
		}
	}

	@Test
	public void test05(){
		WaiterService waiterService = new WaiterServiceImpl();
		System.out.println(waiterService.getWaiterList());
	}

}
