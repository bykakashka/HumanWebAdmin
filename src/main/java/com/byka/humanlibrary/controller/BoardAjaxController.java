package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.data.BookToSessionData;
import com.byka.humanlibrary.data.ModifyBookData;
import com.byka.humanlibrary.data.ModifyBookForm;
import com.byka.humanlibrary.data.SessionData;
import com.byka.humanlibrary.service.BookToSessionService;
import com.byka.humanlibrary.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/ajax/board")
public class BoardAjaxController {

    @Autowired
    private BookToSessionService bookToSessionService;

    @Autowired
    private EventService eventService;

    @GetMapping("/{eventId}/{bookId}")
    public String findBoardsForBookAndEvent(@PathVariable("eventId") Long eventId, @PathVariable("bookId") Long bookId, Model modal) {
        List<BookToSessionData> bookToSessionData = bookToSessionService.getByBookAndEvent(bookId, eventId);
        Map<Long, List<BookToSessionData>> map = bookToSessionData.stream().collect(Collectors.groupingBy(BookToSessionData::getSessionId));

        List<SessionData> sessions = eventService.getSessions(eventId);

        ModifyBookData[] list = new ModifyBookData[sessions.size()];

        sessions.forEach(sessionData -> {
            ModifyBookData bookData = new ModifyBookData();

            Integer sequence = sessionData.getSequence();
            bookData.setSequence(sequence);
            bookData.setSessionId(sessionData.getId());
            bookData.setTime(sessionData.getStartDate() + " - " + sessionData.getEndDate());

            List<BookToSessionData> bookInfos = map.get(sessionData.getId());
            if (bookInfos != null && bookInfos.size() == 1) {
                bookData.setMaxUsers(bookInfos.get(0).getMaxUsers());
                bookData.setBoardNo(bookInfos.get(0).getBoardNo());
            }
            list[sequence - 1] = bookData;
        });

        ModifyBookForm form = new ModifyBookForm();
        form.setData(list);
        modal.addAttribute("eventId", eventId);
        modal.addAttribute("modifyBookForm", form);
        modal.addAttribute("bookId", bookId);
        return "fragments/modifyBookModal";
    }
}