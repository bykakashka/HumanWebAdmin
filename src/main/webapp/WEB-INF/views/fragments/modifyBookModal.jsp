<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal" id="modifyBookBoardsModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="event.modifyBook"/> <h5 class="modal-title"
                                                                                      id="modalTile"></h5></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form:form modelAttribute="modifyBookForm" action="/admin/sessions/modifyBook/${eventId}/${bookId}"
                       id="bookBoardForm">
                <div class="modal-body" id="modalBody">

                    <table class="table table-striped table-bordered">
                        <thead>
                        <th><spring:message code="event.time"/></th>
                        <th><spring:message code="event.boardNo"/></th>
                        <th><spring:message code="event.maxUsers"/></th>
                        </thead>
                        <tbody>
                        <c:forEach var="row" items="${modifyBookForm.data}" varStatus="loop">
                            <c:set var="i" value="${loop.index}"/>

                            <tr>
                                <td>${row.time}</td>
                                <td>
                                    <form:input path="data[${i}].boardNo"
                                                class="js-boardno"/>
                                </td>
                                <td>
                                    <form:input path="data[${i}].maxUsers"
                                                class="js-maxusers"/>
                                </td>

                                <form:hidden path="data[${i}].sessionId"
                                            class="js-maxusers"/>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="saveBookBoard"><spring:message
                            code="common.save"/>
                    </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                            code="common.cancel"/></button>
                </div>

            </form:form>
        </div>
    </div>
</div>