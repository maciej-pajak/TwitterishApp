<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageWrapper title="Messages - ${boxType}">

    <jsp:attribute name="navigation">
        <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Messages - ${boxType}</h1>
            </div>
        </div>
        <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <a href='<c:url value="/message/new"/>' type="button" class="btn btn-success"> New message</a>
                        </div>
                    </div>
                    <c:forEach items="${messages.getContent()}" var="message">
                        
                            <c:choose>
                                <c:when test="${boxType eq 'outbox'}">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <b> TO: ${message.recipient.username}
                                            </b> on ${dateFormatter.format(message.created)}
                                        </div>
                                        <div class="panel-body">
                                            <p>${message.message}  <a href='<c:url value="/message/${message.id}"/>'>Show more</a></p>
                                        </div>
                                    </div>
                                </c:when>    
                                <c:otherwise>
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <b> FROM: ${message.sender.username}
                                            </b> on ${dateFormatter.format(message.created)}
                                            <c:if test="${!message.read}"> - <b>unread</b></c:if>
                                        </div>
                                        <div class="panel-body">
                                            <c:choose>
                                                <c:when test="${message.read}">
                                                    <p>${message.message}</p> 
                                                    <a type="button" class="btn btn-primary btn-sm" 
                                                        href='<c:url value="/message/${message.id}"/>'>Show more</a>
                                                    <a type="button" class="btn btn-success btn-sm" 
                                                        href='<c:url value="/message/new?to=${message.sender.id}"/>'> Reply</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <p><b>${message.message}</b></p> 
                                                    <a type="button" class="btn btn-primary btn-sm" 
                                                        href='<c:url value="/message/${message.id}"/>'>Read</a>
                                                </c:otherwise>
                                            </c:choose>
                                            
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            
                    </c:forEach>
                    <ul class="pager">
                        <c:if test="${messages.hasPrevious()}">
                            <li><a href='<c:url value="?page=${messages.previousPageable().pageNumber + 1}"/>'>See newer messages</a></li>
                        </c:if>
                        <c:if test="${messages.hasNext()}">
                            <li><a href='<c:url value="?page=${messages.nextPageable().pageNumber + 1}"/>'>See older messages</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
    </jsp:body>
</t:pageWrapper>