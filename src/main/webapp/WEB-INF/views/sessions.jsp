<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html data-appid="session">
<div class="container">
    <input type="hidden" value="${event.id}" id="eventId">
    <div>
        <h3><spring:message code="session.event.title"/> &nbsp; ${event.title} </h3>
    </div>

    <p>
    <div>
        <button id="addSession" class="btn btn-primary">
            <spring:message code="event.addSession"/>
        </button>
    </div>
    </p>
    <p>
    <div>
        <c:if test="${not empty map.header or not empty map.row}">
            <table class="table table-striped table-bordered">
                <c:if test="${not empty map.header}">
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
                </c:if>
                <tbody>
                <c:forEach var="bookRow" items="${map.row}">
                    <tr>
                        <td>
                                ${bookRow.bookInfo.name}
                        </td>
                        <c:forEach var="cell" items="${bookRow.rows}">
                            <td width="50px">
                                <c:if test="${not empty cell.boardNo}">
                                    № ${cell.boardNo}
                                </c:if>
                                <br/>
                                <c:if test="${not empty cell.maxUsers}">
                                    ${cell.registeredCount} &nbsp; (${cell.maxUsers})
                                </c:if>
                            </td>
                        </c:forEach>
                        <td>
                            <button class="btn btn-primary js-modifyBookBoardButton"
                                    data-bookid="${bookRow.bookInfo.id}"
                                    data-bookName="${bookRow.bookInfo.name}">
                                <spring:message code="event.modifyBook"/>
                            </button>
                            &nbsp;
                            <form action="/admin/sessions/removeBook/${event.id}" method="post">
                                <input type="hidden" value="${bookRow.bookInfo.id}" name="bookId">

                                <button type="submit" class="btn btn-danger btn-sm  right-button">
                                    <span class="glyphicon glyphicon-minus"></span> <spring:message code="event.removeBook"/>
                                </button>
                            </form>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    </p>
    <p>
        <button id="addBookSessionButton" class="btn btn-primary">
            <spring:message code="session.addBook"/>
        </button>
    </p>
</div>

<div class="modal" id="modifyBookBoardsModal" tabindex="-1" role="dialog">

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