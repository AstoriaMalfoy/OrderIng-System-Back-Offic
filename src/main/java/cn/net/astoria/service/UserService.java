package cn.net.astoria.service;

import cn.net.astoria.domain.Boss;

/**
 * @ClassName UserService
 * @Description 和管理员有关的业务逻辑
 * @Author Astoria
 * @Date 2020/3/3 15:21
 * @Version 1.0
 */
public interface UserService {
    /**
     * 提交用户名和密码 验证用户身份 如果验证通过则返回完整的Boss信息 如果验证未通过则返回null
     * @param username  用户提交的用户名
     * @param password  用户提交的密码
     * @return
     */
    Boss checkUsernameAndPassword(String username,String password);

}
