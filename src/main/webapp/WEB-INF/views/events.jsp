<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html data-appid="event">
<div class="container">
    <button type="button" class="btn btn-primary" id="newEventButton">
        <spring:message code="event.newEvent"/>
    </button>
    <br/>

    <c:forEach var="event" items="${events}">
        <p>
            <b>${event.title} </b>
            <a href="#">
                <i class="ui-icon-pencil js-editevent" data-eventid="${event.id}">*</i>
            </a>
            <br/>
                ${event.city} - ${event.address} - ${event.date}
            <br/>
                ${event.info}
        </p>
    </c:forEach>
</div>


<div class="modal" id="newEvent" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="event.newEvent"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form action="/admin/event/create" method="post" id="eventForm" modelAttribute="newEvent">
                    <form:hidden path="id" id="id"/>

                    <label for="title"><spring:message code="event.title"/></label>
                    <form:input path="title" class="form-control" id="title"/>

                    <label for="info"><spring:message code="event.info"/></label>
                    <form:input path="info" class="form-control" id="info"/>

                    <label for="address"><spring:message code="event.address"/></label>
                    <form:input path="address" class="form-control" id="address"/>

                    <label for="date"><spring:message code="event.date"/></label>

                    <div class="container">
                        <div class="row">
                            <div class='col-sm-6'>
                                <form:input path="date" class="form-control" id='date'/>
                            </div>
                        </div>
                    </div>

                    <label for="city"><spring:message code="event.city"/></label>
                    <form:select path="cityId" class="form-control" id="city">
                        <form:options items="${cities}" itemValue="id" itemLabel="nameEn"/>
                    </form:select>

                    <label for="status"><spring:message code="event.status"/></label>
                    <form:select path="status" class="form-control" id="status">
                        <spring:message code="event.status.new" var="newStatus" />
                        <form:option label="${newStatus}" value="NEW"/>

                        <spring:message code="event.status.regStarted" var="regStarted" />
                        <form:option label="${regStarted}" value="REG_STARTED"/>

                        <spring:message code="event.status.running" var="running" />
                        <form:option label="${running}" value="RUNNING"/>

                        <spring:message code="event.status.closed" var="closed" />
                        <form:option label="${closed}" value="CLOESD"/>
                    </form:select>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveEvent"><spring:message
                        code="common.save"/>
                </button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
</html>
