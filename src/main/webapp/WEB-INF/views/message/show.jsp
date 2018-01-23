<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageWrapper title="Message">

    <jsp:attribute name="navigation">
        <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <c:choose>
                        <c:when test = "${loggedUser.id eq message.sender.id}">
                            Message to ${message.recipient.username} created ${dateFormatter.format(message.created)}
                        </c:when>
                        <c:otherwise>
                            Message from ${message.sender.username} created ${dateFormatter.format(message.created)}
                        </c:otherwise>
                    </c:choose>                 
                </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h4>${message.message}</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${loggedUser.id eq message.recipient.id}">
                    <a href='<c:url value="/message/new?to=${message.sender.id}"/>' type="button" class="btn btn-success btn-outline">Reply</a>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:pageWrapper>