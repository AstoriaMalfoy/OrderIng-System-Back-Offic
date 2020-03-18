package cn.net.astoria.service.impl;

import cn.net.astoria.dao.BossDao;
import cn.net.astoria.dao.impl.BossDaoImpl;
import cn.net.astoria.domain.Boss;
import cn.net.astoria.service.UserService;

/**
 * @ClassName UserServiceImpl
 * @Description 和管理员相关的业务逻辑的具体实现
 * @Author Astoria
 * @Date 2020/3/3 15:24
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    /**
     * 数据库Boss表的操作类
     */
    private BossDao bossDao = new BossDaoImpl();
    @Override
    public Boss checkUsernameAndPassword(String username, String password) {
        return bossDao.CheckUsernameAndPassword(username, password);
    }
}
