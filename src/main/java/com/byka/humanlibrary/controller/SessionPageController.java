package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.converter.ModifyBookFormBuilder;
import com.byka.humanlibrary.data.*;
import com.byka.humanlibrary.exceptions.ValidationException;
import com.byka.humanlibrary.service.BookService;
import com.byka.humanlibrary.service.BookToSessionService;
import com.byka.humanlibrary.service.EventService;
import com.byka.humanlibrary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin/sessions")
public class SessionPageController {
    @Autowired
    private EventService eventService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookToSessionService bookToSessionService;

    @PostMapping("/addBook/{eventId}")
    public String addBook(@PathVariable Long eventId, Model model, SessionBookForm bookForm) {
        eventService.addToCatalog(eventId, bookForm.getBookId());
        return "redirect:/admin/sessions/" + eventId.toString();
    }

    @PostMapping("/removeBook/{eventId}")
    public String removeBook(@PathVariable Long eventId, @RequestParam("bookId") Long bookId) {
        eventService.removeFromCatalog(eventId, bookId);
        return "redirect:/admin/sessions/" + eventId.toString();
    }

    @PostMapping("/add/{eventId}")
    public String addSession(@PathVariable Long eventId, Model model, SessionData sessionData) throws ValidationException {
        updateDates(sessionData, eventId);

        List<SessionData> oldSessions = eventService.getSessions(eventId);
        oldSessions.add(sessionData);
        sessionService.createSessions(eventId, oldSessions);
        return "redirect:/admin/sessions/" + eventId.toString();
    }

    @GetMapping("/{eventId}")
    public String getSessions(@PathVariable Long eventId, Model model) {
        EventData eventData = eventService.getById(eventId);
        List<SessionData> sessionDataList = eventService.getSessions(eventId);

        SessionTable sessionTable = buildSessionTable(eventId, sessionDataList);

        model.addAttribute("map", sessionTable);
        model.addAttribute("event", eventData);
        model.addAttribute("newSession", new SessionData());
        model.addAttribute("sessionBookForm", new SessionBookForm());
        List<BookData> books = bookService.getAll();
        books.sort(Comparator.comparing(BookData::getName));
        model.addAttribute("books", books);
        model.addAttribute("modifyBookForm", new ModifyBookForm());
        return "sessions";
    }

    @GetMapping("/openRegistration/{eventId}/{sessionId}")
    public String openRegistration(@PathVariable Long sessionId, @PathVariable Long eventId) {
        sessionService.startRegistration(sessionId);
        return "redirect:/admin/sessions/" + eventId;
    }

    @GetMapping("/closeRegistration/{eventId}/{sessionId}")
    public String closeRegistration(@PathVariable Long sessionId, @PathVariable Long eventId) {
        sessionService.closeRegistration(sessionId);
        return "redirect:/admin/sessions/" + eventId;
    }

    @PostMapping("/modifyBook/{eventId}/{bookId}")
    public String updateBookSessions(@PathVariable("eventId") Long eventId, @PathVariable("bookId") Long bookId, ModifyBookForm modifyBookForm) {
        List<BookToSessionData> newBoardDataList = ModifyBookFormBuilder.buildBoardData(modifyBookForm, bookId);
        bookToSessionService.updateBookForEvent(eventId, bookId, newBoardDataList);
        return "redirect:/admin/sessions/" + eventId.toString();
    }

    private SessionTable buildSessionTable(Long eventId, List<SessionData> sessionDataList) {
        SessionTable sessionTable = new SessionTable();
        List<SessionRow> rows = buildRows(sessionDataList);
        enrichWithUnusedBooks(eventId, rows, sessionDataList.size());
        rows.sort(Comparator.comparing(SessionRow::getBookInfo));
        sessionTable.setRow(rows);
        sessionTable.setHeader(buildHeader(sessionDataList));
        return sessionTable;
    }

    private void enrichWithUnusedBooks(Long eventId, List<SessionRow> rows, Integer sessionSize) {
        List<BookData> books = eventService.getCatalog(eventId);
        Set<NameIdPair> bookPairs = new HashSet<>();
        books.forEach(b -> bookPairs.add(new NameIdPair(b.getName(), b.getId())));
        rows.forEach(row -> bookPairs.remove(row.getBookInfo()));
        bookPairs.forEach(name -> {
            SessionRow row = new SessionRow();
            row.setBookInfo(name);
            row.setRows(new BookToSessionData[sessionSize]);
            rows.add(row);
        });
    }

    private List<SessionHeader> buildHeader(List<SessionData> sessionDataList) {
        List<SessionHeader> headers = new ArrayList<>(sessionDataList.size());
        sessionDataList.forEach(sessionData -> {
            SessionHeader header = new SessionHeader();
            header.setStartDate(sessionData.getStartDate());
            header.setEndDate(sessionData.getEndDate());
            header.setSequence(sessionData.getSequence());
            header.setSessionId(sessionData.getId());
            header.setRegistrationAvailable(Boolean.TRUE.equals(sessionData.getRegistrationAvailable()));
            headers.add(header);
        });
        return headers;
    }

    private List<SessionRow> buildRows(List<SessionData> sessionDataList) {
        Map<Long, BookToSessionData[]> table = new HashMap<>();
        for (int i = 0; i < sessionDataList.size(); i++) {
            SessionData sessionData = sessionDataList.get(i);
            int finalI = i;
            sessionData.getBooksToSession().forEach(board -> {
                Long bookId = board.getBookId();
                BookToSessionData[] boards = table.get(bookId);
                if (boards == null) {
                    boards = new BookToSessionData[sessionDataList.size()];
                }
                boards[finalI] = board;
                table.put(bookId, boards);
            });
        }


        List<SessionRow> rows = new ArrayList<>();
        table.values().forEach(v -> {
            NameIdPair bookInfo = findBookInfo(v);
            SessionRow row = new SessionRow();
            row.setRows(v);
            row.setBookInfo(bookInfo);
            rows.add(row);
        });
        return rows;
    }

    private NameIdPair findBookInfo(BookToSessionData[] boards) {
        for (BookToSessionData boardData : boards) {
            if (boardData != null) {
                return new NameIdPair(boardData.getBookName(), boardData.getBookId());
            }
        }

        return new NameIdPair();
    }

    private void updateDates(SessionData sessionData, Long eventId) {
        EventData eventData = eventService.getById(eventId);
        String eventDate = eventData.getDate();
        if (eventDate != null) {
            String day = eventDate.split(" ")[0];
            sessionData.setEndDate(day + " " + sessionData.getEndDate());
            sessionData.setStartDate(day + " " + sessionData.getStartDate());
        }
    }
}
