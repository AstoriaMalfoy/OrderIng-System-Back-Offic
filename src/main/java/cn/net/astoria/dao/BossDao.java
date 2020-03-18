package cn.net.astoria.dao;

import cn.net.astoria.domain.Boss;

/**
 * @ClassName BossDao
 * @Description 数据库Boss表的相关操作
 * @Author Astoria
 * @Date 2020/3/3 15:26
 * @Version 1.0
 */
public interface BossDao {
    /**
     * 在数据库中寻找用户名和密码匹配的相关数据项 如果匹配到返回完整的条目 如果没有找到则返回null
     * @param username
     * @param password
     * @return
     */
    Boss CheckUsernameAndPassword(String username,String password);
}
