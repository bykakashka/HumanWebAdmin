const $ = require("jquery");

function showModal() {
    const modal = $('#newBook');

    modal.find('#id').val();
    modal.find('#name').val('');
    modal.find('#longDescription').val('');

    modal.modal('show');
}

function showEdit (id) {
    $.ajax({
        url: "/admin/book/" + id
    }).done(function(result) {
        const modal = $('#newBook');

        modal.removeData();
        modal.find('#id').val(result.id);
        modal.find('#name').val(result.name);
        modal.find('#longDescription').val(result.longDescription);

        modal.modal('show');
    });
}

function initBooks() {
    $('#newBookButton').click(function(){
        showModal();
    });

    $('#saveBook').click(function () {
        $('#bookForm').submit();
    });

    $('.js-editBook').click(function () {
        const bookId = $(this).attr('data-bookid');
        showEdit(bookId);
    });
}

exports.init = initBooks;