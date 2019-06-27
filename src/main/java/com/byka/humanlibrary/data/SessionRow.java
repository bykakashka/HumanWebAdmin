package com.byka.humanlibrary.data;

public class SessionRow {
    private BookToSessionData[] rows;
    private NameIdPair bookInfo;

    public BookToSessionData[] getRows() {
        return rows;
    }

    public void setRows(BookToSessionData[] rows) {
        this.rows = rows;
    }

    public NameIdPair getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(NameIdPair bookInfo) {
        this.bookInfo = bookInfo;
    }
}
