package cn.net.astoria.servlet;

import cn.net.astoria.domain.ResultInfo;
import cn.net.astoria.domain.TableJoinFood;
import cn.net.astoria.service.TableService;
import cn.net.astoria.service.TurnoverService;
import cn.net.astoria.service.impl.TableServiceImpl;
import cn.net.astoria.service.impl.TurnoverServiceImpl;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ClassName TableServlet
 * @Description 处理和参数相关的展示层数据接口
 * @Author Astoria
 * @Date 2020/3/5 22:27
 * @Version 1.0
 */
@WebServlet("/table/*")
public class TableServlet extends BaseServlet {

	private TableService tableService = new TableServiceImpl();
	private TurnoverService turnoverService = new TurnoverServiceImpl();
	/**
	 * 用于获取所有的餐桌信息并展示
	 * @param request
	 * @param response
	 */
	public void getAllTable(HttpServletRequest request,HttpServletResponse response){
		//返回前端的数据格式
		ResultInfo resultInfo  = new ResultInfo();
		Map<Integer, TreeSet<TableJoinFood>> tableMap = new TreeMap<>();
		tableMap = tableService.getAllTableInfo();
		if(tableMap == null){
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("系统内部错误 请联系软件开发者 29044464342qq.com");
		}else{
			resultInfo.setFlag(true);
			resultInfo.setData(tableMap);
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 用户处理上菜
	 * @param request
	 * @param response
	 */
	public void serving(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String tidStr = request.getParameter("tid");
//		设置要上菜的id信息
		int tid = -1;
		if(testParameter(tidStr)){
			tid = Integer.parseInt(tidStr);
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("提交数据有丢失，请重新提交");
			writeObject(resultInfo, response);
			return;
		}
		boolean serBool = tableService.serving(tid);
		if(serBool){
//			成功上菜
			resultInfo.setFlag(true);
		}else{
//			内部错误
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("服务器内部错误，请联系软件开发人员 2904446434@qq.com");
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 用于获取某个餐桌单独的信息
	 * @param request
	 * @param response
	 */
	public void refreshTable(HttpServletRequest request,HttpServletResponse response){
//		定义传送数据的格式
		class TableFormat{
			private int t_num;
			private TreeSet<TableJoinFood> foods;

			public TableFormat(int t_num, TreeSet<TableJoinFood> foods) {
				this.t_num = t_num;
				this.foods = foods;
			}

			@Override
			public String toString() {
				return "tableFormat{" +
						"t_num=" + t_num +
						", foods=" + foods +
						'}';
			}

			public TableFormat() {
			}

			public int getT_num() {
				return t_num;
			}

			public void setT_num(int t_num) {
				this.t_num = t_num;
			}

			public TreeSet<TableJoinFood> getFoods() {
				return foods;
			}

			public void setFoods(TreeSet<TableJoinFood> foods) {
				this.foods = foods;
			}
		}
		TableFormat tableFormat = new TableFormat();
		String tNumStr = request.getParameter("tNum");
		int tNum = -1;
		ResultInfo resultInfo = new ResultInfo();
		if(testParameter(tNumStr)){
			tNum = Integer.parseInt(tNumStr);
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("提交的数据有丢失，请重新发送");
			writeObject(resultInfo, response);
			return;
		}
		TreeSet<TableJoinFood> foods = tableService.getTableInfo(tNum);
		if(foods == null){
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("服务器内部错误 请联系软件开发人员 2904446434@qq.com ");
			writeObject(resultInfo, response);
			return;
		}else{
			tableFormat.setT_num(tNum);
			tableFormat.setFoods(foods);
			resultInfo.setFlag(true);
			resultInfo.setData(tableFormat);
		}
		writeObject(resultInfo, response);
	}


	/**
	 * 用于删除某一桌的菜品
	 * @param request
	 * @param response
	 */
	public void delTableFood(HttpServletRequest request,HttpServletResponse response){
		String tidStr = request.getParameter("tid");
		int tid = -1;
		ResultInfo resultInfo = new ResultInfo();
		if(testParameter(tidStr)){
			tid = Integer.parseInt(tidStr);
			boolean result = tableService.delFood(tid);
			if(result){
				resultInfo.setFlag(true);
			}else{
				resultInfo.setFlag(false);
				resultInfo.setErrorMessage("服务器内部错误 请联系程序开发人员 2904446434@qq.com");
			}
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("上传的数据有丢失 请重新上传");
			writeObject(resultInfo, response);
			return;
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 如果一桌要结账之前用于判断是否可以结账，也就是是否所有菜品都上完了
	 * @param request
	 * @param response
	 */
	public void canInvoicing(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String tNumStr = request.getParameter("tNum");
		if(testParameter(tNumStr)){
			int tNum = Integer.parseInt(tNumStr);
			boolean result = tableService.canInvoicing(tNum);
			if(result){
//				可以结账
				resultInfo.setFlag(true);
			}else{
				resultInfo.setFlag(false);
				resultInfo.setErrorMessage("菜未备齐，不能结账");
				writeObject(resultInfo, response);
				return;
			}

		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("上传数据存在数据丢失，请重试");
			writeObject(resultInfo,response);
			return;
		}
		writeObject(resultInfo, response);
	}

	/**
	 * 结账功能 删除数据数据库table表中所有的相应t_num对应的数据 并且将修改今日营业额
	 * @param request
	 * @param response
	 */
	public void invoicing(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		String tNumStr = request.getParameter("tNum");
		if(testParameter(tNumStr)){
			int tNum = Integer.parseInt(tNumStr);
			int turnover = tableService.getTurnover(tNum);
			turnoverService.addTurnover(turnover);
			boolean result = tableService.invoicing(tNum);
			if(result){
				resultInfo.setFlag(true);
			}else{
				resultInfo.setFlag(false);
				resultInfo.setErrorMessage("服务器内部错误，连续软件开发者 2904446434@qq.com");
			}
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("参数丢失，请重试");
		}
		writeObject(resultInfo, response);
	}
}
