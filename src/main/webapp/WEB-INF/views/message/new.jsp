<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageWrapper title="Message">

    <jsp:attribute name="navigation">
        <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header"> New message </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form:form method="post" modelAttribute="messageForm">
                            <div class="form-group">
                                <label for="recipientField">
                                    Recipient username:
                                </label>
                                <form:input type="text" required="required" path="recipientUsername" class="form-control" id="recipientField"/>  
                                <form:errors path="recipientUsername" element="div"/>
                            </div>
                            <div class="form-group">
                                <label for="messageField">
                                    Message:
                                </label>
                                <form:input type="text" required="required" path="message" class="form-control" id="messageField"/>  
                                <form:errors path="message" element="div"/>
                            </div>
                            <div>
                                <button type="submit" class="btn btn-success">Send</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pageWrapper>