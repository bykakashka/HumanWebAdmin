package com.byka.humanlibrary.data;

import java.util.List;

public class SessionTable {
    private List<SessionHeader> header;

    private List<SessionRow> row;

    public List<SessionHeader> getHeader() {
        return header;
    }

    public void setHeader(List<SessionHeader> header) {
        this.header = header;
    }

    public List<SessionRow> getRow() {
        return row;
    }

    public void setRow(List<SessionRow> row) {
        this.row = row;
    }
}
