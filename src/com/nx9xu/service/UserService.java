package com.nx9xu.service;

import com.nx9xu.pojo.User;

public interface UserService {

    /**
     * 注册
     * @param user
     */
    public void regist(User user);

    /**
     * 登录
     * @param user
     * @return 返回 null 代表在数据库中查询不到该用户的信息，返回非 null 代表查数据库中查询到了该用户即登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可以使用
     * @param username
     * @return 返回 true 代表用户名已存在，返回 false 代表用户名可用
     */
    public boolean existsUsername(String username);
}
