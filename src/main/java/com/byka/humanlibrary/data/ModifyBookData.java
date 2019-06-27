package com.byka.humanlibrary.data;

public class ModifyBookData {
    private Integer maxUsers;
    private Integer boardNo;
    private Integer sequence;
    private Long sessionId;
    private String time;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Integer getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
