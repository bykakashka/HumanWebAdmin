package com.byka.humanlibrary.data;

public class BookRow {
    private boolean isActive;
    private Integer maxUsers;
    private Integer registeredUsers;
    private Integer boardNo;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Integer getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Integer registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public Integer getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }
}
