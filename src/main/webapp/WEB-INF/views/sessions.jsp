<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html data-appid="session">
<div class="container">
    <input type="hidden" value="${event.id}" id="eventId">
    <div>
        ${event.title}
    </div>

    <div>
        <button id="addSession" class="btn btn-primary">
            <spring:message code="event.addSession"/>
        </button>
    </div>

    <div>
        <table border="1">
            <thead>
            <td>
            </td>
            <c:forEach var="h" items="${map.header}">
                <td>
                        ${h.sequence}<br/>
                        ${h.startDate} - ${h.endDate}
                </td>
            </c:forEach>
            <td></td>
            </thead>
            <tbody>

            <c:forEach var="bookRow" items="${map.row}">
                <tr>
                    <td>
                            ${bookRow.bookInfo.name} (${bookRow.bookInfo.id})
                    </td>
                    <c:forEach var="cell" items="${bookRow.rows}">
                        <td width="50px">
                                ${cell.boardNo}
                            <br/>
                                ${cell.maxUsers}
                        </td>
                    </c:forEach>
                    <td>
                        <button class="btn btn-primary js-modifyBookBoardButton" data-bookid="${bookRow.bookInfo.id}">
                            <spring:message code="event.modifyBook"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <button id="addBookSessionButton" class="btn btn-primary">
        <spring:message code="session.addBook"/>
    </button>
</div>

<div class="modal" id="modifyBookBoardsModal" tabindex="-1" role="dialog">
    <input type="hidden" id="modalBookId">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="event.modifyBook"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <table border="1">
                    <thead>
                        <th><spring:message code="event.time"/></th>
                        <th><spring:message code="event.boardNo"/></th>
                        <th><spring:message code="event.maxUsers"/></th>
                    </thead>
                    <tbody>
                    <form:form modelAttribute="modifyBookForm" action="/admin/sessions/modifyBook/${event.id}/" id="bookBoardForm">
                        <c:forEach var="h" items="${map.header}">
                            <c:set var="sequence" value="${h.sequence}"/>
                            <tr>
                                <td>${h.startDate} - ${h.endDate}</td>
                                <td>
                                    <form:input path='data[${sequence}].boardNo' id="boardNo-${h.sequence}"/>
                                </td>
                                <td>
                                    <form:input path="data[${sequence}].maxUsers" id="maxusers-${h.sequence}"/>
                                </td>
                                <input type="hidden" value="${h.sequence}" name="data[${sequence}].sequence"/>${h.sessionId}
                                <input type="hidden" value="${h.sessionId}" name="data[${sequence}].sessionId"/>
                            </tr>
                        </c:forEach>
                    </form:form>
                    </tbody>
                </table>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveBookBoard"><spring:message
                        code="common.save"/>
                </button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>


<div class="modal" id="addBookSessionModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="event.addBook"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form:form action="/admin/sessions/addBook/${event.id}" id="addUpdateBookForm"
                           modelAttribute="sessionBookForm">
                    <form:select path="bookId" class="form-control" id="bookId" required="required">
                        <form:options id="book" items="${books}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </form:form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveBookSession"><spring:message
                        code="common.save"/>
                </button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="newSession" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${event.title}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form:form action="/admin/sessions/add/${event.id}" id="newSessionForm" modelAttribute="newSession">
                    <label for="startDate"><spring:message code="session.startDate"/></label>
                    <form:input path="startDate" class="form-control" id="startDate"/>

                    <label for="endDate"><spring:message code="session.endDate"/></label>
                    <form:input path="endDate" class="form-control" id="endDate"/>
                </form:form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="createSession"><spring:message
                        code="common.save"/>
                </button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>