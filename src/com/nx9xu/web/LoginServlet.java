package com.nx9xu.web;

import com.nx9xu.pojo.User;
import com.nx9xu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class LoginServlet extends HttpServlet {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用 userService.login() 处理登录业务
        User login = userService.login(new User(null, username, password, null));
        if (login == null) { //登录失败
            // 把错误信息和回显的表单项信息保存到 Request 域中
            req.setAttribute("msg", "用户或密码错误！");
            req.setAttribute("username", username);
            // 跳回登录界面
            req.getRequestDispatcher("/loginServlet").forward(req, resp);
        } else { //登录成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
