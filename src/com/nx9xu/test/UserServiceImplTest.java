package com.nx9xu.test;

import com.nx9xu.dao.impl.UserDaoImpl;
import com.nx9xu.pojo.User;
import com.nx9xu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void regist() {
        userService.regist(new User(null,"cx","123","123@qwe.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"cx","123","123@qwe.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("cx"));
    }
}