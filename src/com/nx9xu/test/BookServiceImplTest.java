package com.nx9xu.test;

import com.nx9xu.pojo.Book;
import com.nx9xu.service.BookService;
import com.nx9xu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        System.out.println(books);
    }
}