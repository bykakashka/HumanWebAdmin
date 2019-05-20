package com.byka.humanlibrary.intercaptor;

import com.byka.humanlibrary.controller.EventPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionResolver extends AbstractHandlerExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(EventPageController.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        LOG.error("Handled by resolver", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("trace", e.getMessage());
        return modelAndView;
    }
}
