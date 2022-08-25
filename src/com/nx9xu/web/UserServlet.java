package com.nx9xu.web;

import com.nx9xu.pojo.User;
import com.nx9xu.service.impl.UserServiceImpl;
import com.nx9xu.utils.WebUtils;
import jdk.nashorn.internal.ir.CallNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class UserServlet extends BaseServlet {

    private UserServiceImpl userService = new UserServiceImpl();

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        System.out.println(user);
        String code = req.getParameter("code");

        // 2. 检查验证码是否正确
        if ("abcde".equalsIgnoreCase(code)) { // 验证码正确
            if (userService.existsUsername(user.getUsername())) { // 用户名已存在
                // 把回显信息保存到 Request 域中
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());

                System.out.println("用户名[" + user.getUsername() + "]已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else { // 用户名可用
                // 进行注册
                userService.regist(new User(null, user.getUsername(), user.getPassword(), user.getEmail()));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else { // 验证码不正确
            // 把回显信息保存到 Request 域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        System.out.println(user);

        //调用 userService.login() 处理登录业务
        User login = userService.login(new User(null, user.getUsername(), user.getPassword(), null));
        if (login == null) { //登录失败
            // 把错误信息和回显的表单项信息保存到 Request 域中
            req.setAttribute("msg", "用户或密码错误！");
            req.setAttribute("username", user.getUsername());
            // 跳回登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else { //登录成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
