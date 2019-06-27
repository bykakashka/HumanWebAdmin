package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.BookToSessionData;
import com.byka.humanlibrary.data.ModifyBookData;
import com.byka.humanlibrary.data.ModifyBookForm;

import java.util.ArrayList;
import java.util.List;

public class ModifyBookFormBuilder {
    public static List<BookToSessionData> buildBoardData(ModifyBookForm form, Long bookId) {
        List<BookToSessionData> boardDataList = new ArrayList<>(form.getData().length);
        for (ModifyBookData bookData: form.getData()) {
            if (bookData.getBoardNo() != null || bookData.getMaxUsers() != null) {
                BookToSessionData boardData = new BookToSessionData();
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
