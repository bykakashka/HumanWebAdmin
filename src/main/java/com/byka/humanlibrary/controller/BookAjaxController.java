package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.data.BookData;
import com.byka.humanlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/ajax/book")
public class BookAjaxController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @ResponseBody
    public List<BookData> getBooks() {
        return bookService.getAll();
    }
}
