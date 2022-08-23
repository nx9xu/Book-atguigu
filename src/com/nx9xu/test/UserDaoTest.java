package com.nx9xu.test;

import com.nx9xu.dao.impl.UserDaoImpl;
import com.nx9xu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名不可用");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
            System.out.println("登录失败");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null, "admin11", "admin", "123@qq.com")));
    }
}