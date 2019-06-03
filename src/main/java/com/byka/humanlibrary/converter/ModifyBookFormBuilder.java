package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.BoardData;
import com.byka.humanlibrary.data.ModifyBookData;
import com.byka.humanlibrary.data.ModifyBookForm;

import java.util.ArrayList;
import java.util.List;

public class ModifyBookFormBuilder {
    public static List<BoardData> buildBoardData(ModifyBookForm form, Long bookId) {
        List<BoardData> boardDataList = new ArrayList<>(form.getData().length);
        for (ModifyBookData bookData: form.getData()) {
            if (bookData.getBoardNo() != null) {
                BoardData boardData = new BoardData();
                boardData.setSessionId(bookData.getSessionId());
                boardData.setBookId(bookId);
                boardData.setBoardNo(bookData.getBoardNo());
                boardData.setMaxUsers(bookData.getMaxUsers());
                boardDataList.add(boardData);
            }
        }

        return boardDataList;
    }
}
