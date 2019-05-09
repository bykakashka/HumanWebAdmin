package com.byka.humanlibrary.controller;

import org.springframework.ui.Model;

// TODO should be interceptor
public class AbstractAdminController {
    public String setContentIntoPage(Model model, String page) {
        model.addAttribute("page", page);
        model.addAttribute("title", "Живая библиотека");
        return "pageTemplate";
    }
}
