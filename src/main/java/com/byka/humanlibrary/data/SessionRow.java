package com.byka.humanlibrary.data;

public class SessionRow {
    private BoardData[] rows;
    private NameIdPair bookInfo;

    public BoardData[] getRows() {
        return rows;
    }

    public void setRows(BoardData[] rows) {
        this.rows = rows;
    }

    public NameIdPair getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(NameIdPair bookInfo) {
        this.bookInfo = bookInfo;
    }
}
