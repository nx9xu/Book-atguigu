package com.nx9xu.web;

import com.nx9xu.pojo.User;
import com.nx9xu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2. 检查验证码是否正确
        if ("abcde".equalsIgnoreCase(code)) { // 验证码正确
            if (userService.existsUsername(username)) { // 用户名已存在
                System.out.println("用户名[" + username + "]已存在");
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            } else { // 用户名可用
                // 进行注册
                userService.regist(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
            }
        } else { // 验证码不正确
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
