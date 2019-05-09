package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.service.BookService;
import com.byka.humanlibrary.data.BookData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/book")
public class BookPageController extends AbstractAdminController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String getBooks(final Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("newBook", new BookData());
        return super.setContentIntoPage(model, "books");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookData getBookById(@PathVariable("id") Long bookId) {
        return bookService.getById(bookId);
    }


    @PostMapping("create")
    public String addBook(final Model model, @ModelAttribute("newBook") final BookData newBook) {
        bookService.createOrUpdateBook(newBook);
        return "redirect:/admin/book";
    }
}
