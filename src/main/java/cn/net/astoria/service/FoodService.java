package cn.net.astoria.service;

import cn.net.astoria.domain.Food;
import cn.net.astoria.domain.PageBean;
import cn.net.astoria.domain.ResultInfo;

import java.util.List;

/**
 * @ClassName FoodService
 * @Description 处理和菜品有关的业务逻辑
 * @Author Astoria
 * @Date 2020/3/3 20:28
 * @Version 1.0
 */
public interface FoodService {
    /**
     * 查询所有的菜品信息 返回菜品信息的list集合
     * @return
     */
    List<Food> getAllFood();

    /**
     * 获取分页信息
     * @return
     */
    PageBean<Food> getListPaging(int currentPage, int pageSize);

    /**
     * 根据Id获取菜品的详细信息
     * @param fid
     * @return
     */
    Food getFoodById(int fid);

    /**
     * 根据传入的Food对原有的Food进行更新 返回是否更新成功
     * @param food
     * @return
     */
	boolean updateFood(Food food);

    /**
     * 创建新的菜品信息
     * @param food
     * @return
     */
    ResultInfo insertFood(Food food);

    /**
     * 根据fid删除相关的菜品信息
     * @param fid
     * @return
     */
    ResultInfo deleteFood(int fid);



}
