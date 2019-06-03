package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.data.BoardData;
import com.byka.humanlibrary.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/ajax/board")
public class BoardAjaxController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/{eventId}/{bookId}")
    @ResponseBody
    public List<BoardData> findBoardsForBookAndEvent(@PathVariable("eventId") Long eventId, @PathVariable("bookId") Long bookId) {
        return boardService.findBoardsForBookAndEvent(bookId, eventId);
    }
}
