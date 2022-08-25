package com.nx9xu.web;

import com.nx9xu.pojo.Book;
import com.nx9xu.service.BookService;
import com.nx9xu.service.impl.BookServiceImpl;
import com.nx9xu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用bookService.addBook()保存图书
        bookService.addBook(book);
        // 跳到图书列表页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // 获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService.deleteBookById()删除图书
        bookService.deleteBookById(id);
        // 跳转到图书列表界面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        req.setAttribute("books", books);
        //3 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

}
