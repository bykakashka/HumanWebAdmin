const $ = require("jquery");

function initSessions() {
    $("#addSession").click(function () {
        $("#newSession").modal('show');
    });

    $("#createSession").click(function () {
        $("#newSessionForm").submit();
    });

    $("#addBookSessionButton").click(function () {
        const modal = $('#addBookSessionModal');
        modal.find("#bookId").val('');
        modal.modal('show');
    });

    $("#saveBookSession").click(function () {
        $("#addUpdateBookForm").submit();
    });


    $('.js-modifyBookBoardButton').click(function () {
        const bookId = $(this).attr('data-bookid');
        const modal = $('#modifyBookBoardsModal');
        $('#modalBookId').val(bookId);

        const eventId = $('#eventId').val();
        $.ajax({
            url: "/admin/ajax/board/" + eventId + "/" + bookId
        }).done(function (result) {
            modal.modal('show');

            result.forEach(v => {
                const boardNo = modal.find('#boardNo-' + v.sessionId);
                if (boardNo) {
                    boardNo.val(v.boardNo);
                } else {
                    console.log('Cannot find boardNo field');
                }

                const maxUsers = modal.find('#maxusers-' + v.sessionId);
                if (maxUsers) {
                    maxUsers.val(v.maxUsers);
                } else {
                    console.log('Cannot find boardNo field');
                }
            });
        });
    });

    $('#saveBookBoard').click(function () {
        const form = $('#bookBoardForm');
        form.attr('action', '/admin/sessions/modifyBook/' + $('#eventId').val() + '/' + $('#modalBookId').val());
        form.submit();
    })
}

exports.init = initSessions;