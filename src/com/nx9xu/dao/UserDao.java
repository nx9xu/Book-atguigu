package com.nx9xu.dao;

import com.nx9xu.pojo.User;

public interface UserDao {



    /**
     * 通过 username 查询用户信息
     * @param username 用户名
     * @return 如果返回 null 则表示没有这个用户。反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 通过 username 和 password 查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 如果返回 null 则说明用户名和密码错误。反之亦然
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息到数据库中
     * @param user 用户名
     * @return 返回 -1 代表失败，其他是指 sql 语句对数据库的影响行数
     */
    public int saveUser(User user);

}
