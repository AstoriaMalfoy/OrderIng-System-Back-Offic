package cn.net.astoria.service.impl;

import cn.net.astoria.dao.FoodDao;
import cn.net.astoria.dao.FoodSalesDao;
import cn.net.astoria.dao.TableDao;
import cn.net.astoria.dao.impl.FoodDaoImpl;
import cn.net.astoria.dao.impl.FoodSalesDaoImpl;
import cn.net.astoria.dao.impl.TableDaoImpl;
import cn.net.astoria.domain.Food;
import cn.net.astoria.domain.PageBean;
import cn.net.astoria.domain.ResultInfo;
import cn.net.astoria.service.FoodService;
import org.omg.CORBA.StringHolder;

import javax.sound.midi.SysexMessage;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName FoodServiceImpl
 * @Description 和食物有关的业务逻辑的实现类
 * @Author Astoria
 * @Date 2020/3/3 20:29
 * @Version 1.0
 */
public class FoodServiceImpl implements FoodService {

    private FoodDao foodDao = new FoodDaoImpl();
    private TableDao tableDao = new TableDaoImpl();
    private FoodSalesDao foodSalesDao = new FoodSalesDaoImpl();

    @Override
    public List<Food> getAllFood() {
        List<Food> foods = foodDao.getAll();
        return foods;
    }

    @Override
    public PageBean<Food> getListPaging(int currentPage, int pageSize) {
        PageBean<Food> pageBean = new PageBean<>();
        List<Food> foods = null;
        foods = foodDao.getListPaging((currentPage-1)*pageSize,pageSize);
//        设置list集合
        pageBean.setListData(foods);
//        如果List非空 遍历获取每个菜品当月销售量
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        String halfKey = simpleDateFormat.format(System.currentTimeMillis());
        String key;
        for (Food food : foods) {
            String idKey = null;
            if(food.getF_id()<10){
                idKey = "00" + food.getF_id();
            }else if(food.getF_id()<100){
                idKey = "0" + food.getF_id();
            }else{
                idKey = "" + food.getF_id();
            }
            key = halfKey + idKey;
            food.setMonthSell(foodSalesDao.getValue(key));
        }
//        设置当前页面
        pageBean.setCurrengPage(currentPage);
//        设置每一页的记录数目
        pageBean.setPageSize(pageSize);
//        设置记录总数
        int listCount = foodDao.getListCount();
        pageBean.setListCount(listCount);
//        设置总页数
        int pageCount = listCount%pageSize==0?listCount/pageSize:(listCount/pageSize)+1;
        pageBean.setPageCount(pageCount);

        return pageBean;
    }

    @Override
    public Food getFoodById(int fid) {
        Food food = null;
        food = foodDao.getFoodById(fid);
        return food;
    }

    @Override
    public boolean updateFood(Food food) {
        return foodDao.updateFood(food);
    }

    @Override
    public ResultInfo insertFood(Food food) {
        ResultInfo resultInfo = new ResultInfo();
//        首先从数据库中查找有没有同名的菜品
        Food sameNameFood = foodDao.getFoodByName(food.getF_name());
        if(sameNameFood == null){
            boolean  result = foodDao.insertFood(food);
            if(result){
                resultInfo.setFlag(true);
            }else{
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("菜品添加错误，请联系软件开发者 2904446434@qq.com");
            }
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("菜品已经存在，无法添加");
        }
        return resultInfo;
    }

    @Override
    public ResultInfo deleteFood(int fid) {
        ResultInfo resultInfo = new ResultInfo();
//        首先判断在上菜表中是否存在该记录，如果存在给记录则不能删除
        int foodCount = tableDao.getFoodCount(fid);
        if(foodCount == 0){
//                没有未处理的订单 可以进行删除操作
            boolean result = foodDao.deleteFood(fid);
            if(result){
//                删除成功
                resultInfo.setFlag(true);
            }else{
//                删除失败
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("服务器内部错误 请联系软件开发者 2904446434@qq.com");
            }
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("该菜品还有未处理结束的订单，不能进行删除");
            return resultInfo;
        }
        return resultInfo;
    }

}
