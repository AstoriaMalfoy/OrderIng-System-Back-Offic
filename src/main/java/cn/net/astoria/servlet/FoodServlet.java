package cn.net.astoria.servlet;

import cn.net.astoria.dao.impl.FoodSalesDaoImpl;
import cn.net.astoria.domain.*;
import cn.net.astoria.service.FoodSalesService;
import cn.net.astoria.service.FoodService;
import cn.net.astoria.service.TableService;
import cn.net.astoria.service.impl.FoodSalesServiceImpl;
import cn.net.astoria.service.impl.FoodServiceImpl;
import cn.net.astoria.service.impl.TableServiceImpl;
import com.google.protobuf.DescriptorProtos;
import org.codehaus.jackson.map.ser.std.StaticListSerializerBase;
import org.codehaus.jackson.map.ser.std.StdArraySerializers;
import org.springframework.beans.factory.wiring.ClassNameBeanWiringInfoResolver;
import org.springframework.jdbc.core.ResultSetExtractor;
import sun.java2d.pipe.SpanIterator;
import sun.text.resources.in.FormatData_in_ID;

import javax.print.event.PrintJobAttributeEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.util.List;

/**
 * @ClassName FoodServlet
 * @Description 处理和菜品相关的servlet接口
 * @Author Astoria
 * @Date 2020/3/3 20:18
 * @Version 1.0
 */
@WebServlet("/food/*")
public class FoodServlet extends BaseServlet {

    private FoodService foodService = new FoodServiceImpl();
    private FoodSalesService foodSalesService = new FoodSalesServiceImpl();
    private TableService tableService = new TableServiceImpl();

    /**
     * 用于获取所有的菜品信息 提供分页
     * @param request
     * @param response
     */
    public void getAll(HttpServletRequest request ,HttpServletResponse response){
        List<Food> foods = null;
//        首先获取用户提交的参数
        String userPostCurrentPage = request.getParameter("currentPage");
        int currentPage = 1;
        if(testParameter(userPostCurrentPage)){
            currentPage = Integer.parseInt(userPostCurrentPage);
        }

        String userPostPageSize = request.getParameter("pageSize");
        int pageSize = 10;
        if(testParameter(userPostPageSize)){
            pageSize = Integer.parseInt(userPostPageSize);
        }
//        获取分页的PageBean对象
        PageBean<Food> pageBean = foodService.getListPaging(currentPage, pageSize);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setData(pageBean);

//        将查询结构返回给客户端
        writeObject(resultInfo, response);
    }

    /**
     * 用户获取一个菜品的详细信息
     * @param request
     * @param response
     */
    public void getFoodInfo(HttpServletRequest request,HttpServletResponse response){
        String fidStr = request.getParameter("fid");
        int fid = -1;
        ResultInfo resultInfo = new ResultInfo();
        if(testParameter(fidStr)){
//            获取到有效的菜品编号
            fid = Integer.parseInt(fidStr);
            Food food = null;
            food = foodService.getFoodById(fid);
            if(food == null){
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("未从数据库中获取到相关的表项，请检查传入的参数是否正确");
            }else{
                resultInfo.setFlag(true);
                resultInfo.setData(food);
            }
        }else{
//            菜品编号无效
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("未选择菜品信息，请重新操作");
        }
        writeObject(resultInfo,response);
    }


    /**
     * 根据用户提交的内容修改菜品的详细信息
     * @param request
     * @param response
     */
    public void updateFood(HttpServletRequest request,HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();
//        获取fid信息
        String fidStr = request.getParameter("fid");
        int fid = -1;
        if(testParameter(fidStr)){
            fid = Integer.parseInt(fidStr);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的数据有丢失，请重新提交");
            writeObject(resultInfo, response);
            return;
        }
//        获取name信息
        String nameStr = request.getParameter("name");
        String name = null;
        if(testParameter(nameStr)){
                name = nameStr;
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的数据有丢失，请重新提交");
            writeObject(resultInfo, response);
            return;
        }
//        获取price信息
        String priceStr = request.getParameter("price");
        double price = -1;
        if(testParameter(priceStr)){
            price = Double.parseDouble(priceStr);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的数据有丢失，请重新提交");
            writeObject(resultInfo, response);
            return;
        }

        boolean result;
        Food food = new Food();
        food.setF_id(fid);
        food.setF_name(name);
        food.setF_price((int) price);
//        对数据进行更新
        result = foodService.updateFood(food);
        if(result){
            resultInfo.setFlag(true);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("数据更新错误，请联系开发者，2904446434@qq.com");
        }
        writeObject(resultInfo, response);
    }


    /**
     * 创建新的菜品信息
     * @param request
     * @param response
     */
    public void newFood(HttpServletRequest request,HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();
//        获取菜品名称
        String nameStr = request.getParameter("name");
        String name = null;
        System.out.println(nameStr);
        if(testParameter(nameStr)){
            name = nameStr;
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的数据有丢失，请重新提交 name");
            writeObject(resultInfo, response);
            return;
        }
//        获取菜品价格
        String priceStr = request.getParameter("price");
        int price = -1;
        System.out.println(priceStr);
        if(testParameter(priceStr)){
            price = Integer.parseInt(priceStr);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的数据有丢失，请重新提交 price");
        }


        Food food = new Food();
        food.setF_name(name);
        food.setF_price(price);
        resultInfo = foodService.insertFood(food);
        writeObject(resultInfo, response);
    }

    /**
     * 删除菜品信息以及所有相关的信息
     * @param request
     * @param response
     */
    public void deleteFood(HttpServletRequest request ,HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();
        String fidStr = request.getParameter("fid");
        int fid = -1;
        if(testParameter(fidStr)){
            fid = Integer.parseInt(fidStr);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的信息有丢失，请重新提交");
        }
        resultInfo = foodService.deleteFood(fid);
        writeObject(resultInfo, response);
    }


    /**
     * 获取菜品的详细信息
     * @param request
     * @param response
     */
    public void getSales(HttpServletRequest request,HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();
        String fidStr = request.getParameter("fid");
        int fid = -1;
        if(testParameter(fidStr)){
            fid = Integer.parseInt(fidStr);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("提交的信息有丢失，请重新提交");
            writeObject(resultInfo, response);
            return;
        }
        /**
         * 定义内部类 用于存储返回数据的结构信息
         */
        class ResultFormat{
            private List<FoodSales> foodSales;
            private String name;
            private int price;

            public ResultFormat(List<FoodSales> foodSales, String name, int price) {
                this.foodSales = foodSales;
                this.name = name;
                this.price = price;
            }

            public ResultFormat() {}

            public List<FoodSales> getFoodSales() { return foodSales; }

            public void setFoodSales(List<FoodSales> foodSales) { this.foodSales = foodSales; }

            public String getName() { return name; }

            public void setName(String name) { this.name = name; }

            public int getPrice() { return price; }

            public void setPrice(int price) { this.price = price; }
        }

        ResultFormat resultFormat = new ResultFormat();
//        首先获取food信息
        Food food = foodService.getFoodById(fid);
        if(food!=null){
            resultFormat.setName(food.getF_name());
            resultFormat.setPrice(food.getF_price());
//            获取近所有的销售记录
            List<FoodSales> sales = foodSalesService.getSales(fid);
            if(sales!=null){
                resultFormat.setFoodSales(sales);
                resultInfo.setFlag(true);
                resultInfo.setData(resultFormat);
            }else{
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("服务器内部错误 请联系程序开发人员 2904446434@qq.com");
                writeObject(resultInfo, response);
                return;
            }
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("服务器内部错误 请联系程序开发人员 2904446434@qq.com");
            writeObject(resultInfo, response);
            return;
        }
        writeObject(resultInfo, response);
    }

    /**
     * 用于获取所有菜品的待上菜信息
     * @param request
     * @param response
     */
    public void getFoodList(HttpServletRequest request ,HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();
        List<FoodOrder> foodOrders = null;
        foodOrders = tableService.getFoodOrder();
        if(foodOrders == null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("目前无待上菜的订单");
        }else{
            resultInfo.setFlag(true);
            resultInfo.setData(foodOrders);
        }
        writeObject(resultInfo, response);
    }
}
