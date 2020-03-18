package cn.net.astoria.dao;

import cn.net.astoria.domain.Food;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName FoodDao
 * @Description 数据库Food表的操作接口
 * @Author Astoria
 * @Date 2020/3/3 20:30
 * @Version 1.0
 */
public interface FoodDao {


    /**
     * 获取所有的菜品信息
     * @return
     */
    List<Food> getAll();

    /**
     * 获取列表的分页信息
     * @return
     */
    List<Food> getListPaging(int start,int size);

    /**
     * 获取菜品的数量
     * @return
     */
    int getListCount();

    /**
     * 根据ID从数据库中获取食物信息
     * @param fid
     * @return
     */
    Food getFoodById(int fid);


    /**
     * 根据提交的Food对数据库的food进行更新
     * @param food
     * @return
     */
    boolean updateFood(Food food);

    /**
     * 根据提交的food信息在数据库中创建新的
     * @param food
     * @return
     */
    boolean insertFood(Food food);

    /**
     * 从数据库中根据名字查询菜品信息
     * @param f_name
     * @return
     */
    Food getFoodByName(String f_name);

    /**
     * 根据相应的fid删除对应的菜品 返回删除是否成功
     * @param fid
     * @return
     */
    boolean deleteFood(int fid);
}
