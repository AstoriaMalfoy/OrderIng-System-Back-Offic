package cn.net.astoria.servlet;

import cn.net.astoria.domain.ResultInfo;
import cn.net.astoria.service.TurnoverService;
import cn.net.astoria.service.impl.TurnoverServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName TodayMoney
 * @Description 获取营业额接口
 * @Author Astoria
 * @Date 2020/3/10 16:40
 * @Version 1.0
 */
@WebServlet("/turnover/*")
public class Turnover extends BaseServlet {
	private TurnoverService turnoverService = new TurnoverServiceImpl();

	/**
	 * 获取今天的营业额
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void todayMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultInfo resultInfo = new ResultInfo();
		int money = turnoverService.getMoney();
		resultInfo.setFlag(true);
		resultInfo.setData(money);
		response.setContentType("application/json;charset=utf-8");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), resultInfo);
	}

	public void recently(HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		class DateFormat{
			private String date;
			private int turnover;

			public DateFormat(String date, int turnover) {
				this.date = date;
				this.turnover = turnover;
			}

			public DateFormat() {
			}

			public String getDate() {
				return date;
			}

			public void setDate(String date) {
				this.date = date;
			}

			public int getTurnover() {
				return turnover;
			}

			public void setTurnover(int turnover) {
				this.turnover = turnover;
			}
		}
		DateFormat[] dateFormats = new DateFormat[15];
		List<Integer> turnoverList = null;
		turnoverList = turnoverService.getRecently();
		if(turnoverList != null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			for (int i = 0; i <turnoverList.size() ; i++) {
				dateFormats[i] = new DateFormat();
				dateFormats[i].setDate(simpleDateFormat.format(System.currentTimeMillis() - (i*1000*3600*24)));
				dateFormats[i].setTurnover(turnoverList.get(i));
			}
			resultInfo.setFlag(true);
			resultInfo.setData(dateFormats);
		}else{
			resultInfo.setFlag(false);
			resultInfo.setErrorMessage("服务器内部错误，请联系软件开发者 2904446434@qq.com");
		}
		writeObject(resultInfo, response);
	}



}
