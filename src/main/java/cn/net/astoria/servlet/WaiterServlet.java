package cn.net.astoria.servlet;

import cn.net.astoria.domain.ResultInfo;
import cn.net.astoria.domain.Waiter;
import cn.net.astoria.domain.WaiterPer;
import cn.net.astoria.service.WaiterService;
import cn.net.astoria.service.impl.WaiterServiceImpl;
import com.sun.org.apache.regexp.internal.REUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.awt.geom.FlatteningPathIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WaiterServlet
 * @Description 服务员相关操作的Servler
 * @Author Astoria
 * @Date 2020/3/9 17:41
 * @Version 1.0
 */
@WebServlet("/waiter/*")
public class WaiterServlet extends BaseServlet {
	private WaiterService waiterService = new WaiterServiceImpl();

	/**
	 * 用于获取服务员预览
	 * @param request
	 * @param response
	 */
	public void getUserPreview(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		List<Waiter> waiters = waiterService.getWaiterList();
		if(waiters == null){
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者 2904446434@qq.com");
		}else{
			resultInfo.setFlag(true);
			resultInfo.setData(waiters);
		}
		writeObject(resultInfo, response);
	}


	/**
	 * 用于获取服务员的详细销量数据
	 * @param request
	 * @param response
	 */
	public void getDetail(HttpServletRequest request ,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String widStr = request.getParameter("w_id");
		if(testParameter(widStr)){
			int wid = Integer.parseInt(widStr);
			//定义内部数据传输格式
			class DaySales{
				private String formateDate;
				private int dayOrderCount;

				public DaySales() {
				}

				public DaySales(String formateDate, int dayOrderCount) {
					this.formateDate = formateDate;
					this.dayOrderCount = dayOrderCount;
				}

				public String getFormateDate() {
					return formateDate;
				}

				public void setFormateDate(String formateDate) {
					this.formateDate = formateDate;
				}

				public int getDayOrderCount() {
					return dayOrderCount;
				}

				public void setDayOrderCount(int dayOrderCount) {
					this.dayOrderCount = dayOrderCount;
				}
			}
			List<DaySales> result = new ArrayList<>();
			List<Integer> monthSalesList =  waiterService.getWaiterMonthSales(wid);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
			String baseString = simpleDateFormat.format(System.currentTimeMillis());
			for(int i=0;i<monthSalesList.size();++i){
				StringBuilder month = new StringBuilder();
				month.append(baseString);
				if((i+1)<10){
					month.append("0").append((i+1));
				}else{
					month.append((i+1));
				}
				result.add(new DaySales(month.toString(),monthSalesList.get(i)));
			}
			resultInfo.setFlag(true);
			resultInfo.setData(result);
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("数据丢失，请重试");
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 用于获取服务员数量
	 * @param request
	 * @param response
	 */
	public void getWaiterCount(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		int count = waiterService.getUserCount();
		resultInfo.setFlag(true);
		resultInfo.setData(count);
		writeObject(resultInfo, response);
	}

	/**
	 * 获取每天每天销售的数量
	 * @param request
	 * @param response
	 */
	public void getSalesCount(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		int count = waiterService.getTodayFoodCount();
		resultInfo.setFlag(true);
		resultInfo.setData(count);
		writeObject(resultInfo , response);
	}

	/**
	 * 获取员工销量排行
	 * @param request
	 * @param response
	 */
	public void getHotWaiter(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		List<WaiterPer> waiters = null;
		waiters = waiterService.getHotWaiter();
		if(waiters == null){
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者 2904446434@qq.com");
		}else{
			resultInfo.setFlag(true);
			resultInfo.setData(waiters);
		}
		writeObject(resultInfo, response);
	}


	/**
	 * 用于获取服务员的用户名和密码等列表信息
	 * @param request
	 * @param response
	 */
	public void getWaiterList(HttpServletRequest request,HttpServletResponse response){
		List<Waiter> waiters = null;
		ResultInfo resultInfo = new ResultInfo();
		waiters = waiterService.getWaiterListAndPassword();
		if(waiters == null){
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者，2904446434@qq.com");
		}else{
			resultInfo.setFlag(true);
			resultInfo.setData(waiters);
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 创建新的服务员
	 * @param request
	 * @param response
	 */
	public void newWaiter(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String name = request.getParameter("name");
		if(testParameter(name)){
			String password = request.getParameter("password");
			if(testParameter(password)){
				boolean result = waiterService.newWaiter(name,password);
				if(result){
					resultInfo.setFlag(true);
				}else{
					resultInfo.setFlag(false);
					resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者 2904446434@qq.com");
				}
			}else{
				resultInfo.setFlag(false);
				resultInfo.setErrorMessage("数据丢失，请重试");
			}
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("数据丢失，请重试");
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 用于更新服务员信息
	 * @param request
	 * @param response
	 */
	public void updateWaiter(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String widStr = request.getParameter("w_id");
		if(testParameter(widStr)){
			int wid = Integer.parseInt(widStr);
			String name = request.getParameter("name");
			if(testParameter(name)){
				String password = request.getParameter("password");
				if(testParameter(password)){
					boolean result = waiterService.updateWaiter(wid,name,password);
					if(result){
						resultInfo.setFlag(true);
					}else{
						resultInfo.setFlag(false);
						resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者 2904446434@qq.com");
					}
				}else{
					resultInfo.setFlag(false);
					resultInfo.setErrorMessage("数据丢失，请重试");
				}
			}else{
				resultInfo.setFlag(false);
				resultInfo.setErrorMessage("数据丢失，请重试");
			}
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("数据丢失，请重试");
		}
		writeObject(resultInfo, response);
	}


	public void deleteWaiter(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String w_id = request.getParameter("w_id");
		if(testParameter(w_id)){
			boolean result = waiterService.deleteService(Integer.parseInt(w_id));
			if(result){
				resultInfo.setFlag(true);
			}else{
				resultInfo.setFlag(false);
				resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者 2904446434@qq.com");
			}
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("数据丢失，请重试");
		}
		writeObject(resultInfo, response);
	}
}
