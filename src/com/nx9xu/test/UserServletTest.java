package com.nx9xu.test;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class UserServletTest {

    @Test
    public void regist() {
        System.out.println("这是regist()方法被调用了");
    }

    @Test
    public void login() {
        System.out.println("这是login()方法被调用了");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String action = "regist";
        Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
        declaredMethod.invoke(new UserServletTest());
    }
}