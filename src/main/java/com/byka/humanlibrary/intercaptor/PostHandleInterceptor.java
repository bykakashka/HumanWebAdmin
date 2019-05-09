package com.byka.humanlibrary.intercaptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostHandleInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String viewName = modelAndView.getViewName();
            if (viewName != null && !viewName.startsWith("redirect:")) {
                modelAndView.addObject("page", viewName);
                modelAndView.addObject("title", "Живая библиотека");
                modelAndView.setViewName("pageTemplate");
            }
        }

    }


}
