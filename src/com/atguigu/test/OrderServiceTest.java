package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(100),new BigDecimal(100)));
        orderService.createOrder(cart, 1);
    }
}