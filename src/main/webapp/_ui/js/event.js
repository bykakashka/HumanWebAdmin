const $ = require("jquery");

function showModal() {
    const modal = $('#newEvent');

    modal.find('#id').val('');
    modal.find('#title').val('');
    modal.find('#info').val('');
    modal.find('#date').val('');
    modal.find('#address').val('');
    modal.find('#city').val('');
    modal.find('#status').val('NEW');
    modal.modal('show');
}

function showEdit (id) {

    $.ajax({
        url: "/admin/event/" + id
    }).done(function(result) {
        const modal = $('#newEvent');

        modal.removeData();
        modal.find('#id').val(result.id);
        modal.find('#title').val(result.title);
        modal.find('#info').val(result.info);
        modal.find('#address').val(result.address);
        modal.find('#date').val(result.date);
        modal.find('#city').val(result.cityId);

        modal.modal('show');
    });
}

function initEvents() {
    $('#newEventButton').click(function() {
        showModal();
    });

    $('#saveEvent').click(function() {
        $('#eventForm').submit();
    });

    $('.js-editevent').click(function() {
        const eventId = $(this).attr('data-eventid');
        showEdit(eventId);
    })
}

exports.init = initEvents;