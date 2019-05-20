package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.data.EventData;
import com.byka.humanlibrary.service.CityService;
import com.byka.humanlibrary.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/event")
public class EventPageController {
    private static final Logger LOG = LoggerFactory.getLogger(EventPageController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public String getEvents(final Model model) {
        model.addAttribute("events", eventService.getLatest(10));
        model.addAttribute("newEvent", new EventData());
        model.addAttribute("cities", cityService.getAll());
        return "events";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EventData getEvents(final @PathVariable("id") Long id) {
        return eventService.getById(id);
    }

    @PostMapping("create")
    public String createOrUpdate(final @ModelAttribute("event")EventData event) {
        eventService.createOrUpdate(event);
        return "redirect:/admin/event";
    }
}
