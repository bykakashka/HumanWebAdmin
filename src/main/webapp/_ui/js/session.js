const $ = require("jquery");

function modifyBook() {
    $('.js-modifyBookBoardButton').click(function () {
        const bookId = $(this).attr('data-bookid');
        $('#modalBookId').val(bookId);

        // document.getElementById("modalTile").innerHTML = '&nbsp;' + $(this).attr('data-bookName');

        const eventId = $('#eventId').val();
        $.ajax({
            url: "/admin/ajax/board/" + eventId + "/" + bookId
        }).done(function (result) {
            $('#modifyBookBoardsModal').replaceWith(result);
            $('#modifyBookBoardsModal').modal('show');
        });
    });
}

function initSessions() {
    // cleanModalOnHide();

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

    modifyBook();

    $('#saveBookBoard').click(function () {
        const form = $('#bookBoardForm');
        console.log(form);
        form.attr('action', '/admin/sessions/modifyBook/' + $('#eventId').val() + '/' + $('#modalBookId').val());
        form.submit();
    })
}

function cleanModalOnHide() {
    $('#modifyBookBoardsModal').on('hidden.bs.modal', function (e) {
        $(this)
            .find("input,textarea,select")
            .val('')
            .end()
            .find("input[type=checkbox], input[type=radio]")
            .prop("checked", "")
            .end();
    })
}

exports.init = initSessions;