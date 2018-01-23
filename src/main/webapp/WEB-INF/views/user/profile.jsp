<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageWrapper title="${user.username}'s profile">

    <jsp:attribute name="navigation">
        <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${user.username}'s profile</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="well">
                        <h4>Email: ${user.email}</h4>
                        <c:if test="${user.id ne loggedUser.id}">
                            <a type="button" class="btn btn-info" href='<c:url value="/message/new?to=${user.id}"/>'>Send message</a>
                        </c:if>
                    </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header">Recently tweeted:</h3>
            </div>
        </div>
        <div class="row">
                <div class="col-lg-12">
                    <c:forEach items="${tweets.getContent()}" var="tweet">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <a href='<c:url value="/tweet/${tweet.id}"/>'>
                                    <b>${tweet.user.username}</b> tweeted on ${dateFormatter.format(tweet.created)}
                                </a>
                            </div>
                            <div class="panel-body">
                                <p>${tweet.text}</p>
                                <p><a href='<c:url value="/tweet/${tweet.id}"/>'>
                                    <i>${tweet.commentsCount} comment<c:if test="${tweet.commentsCount ne 1}">s</c:if></i>
                                </a></p>
                            </div>
                        </div>
                    </c:forEach>
                    <ul class="pager">
                        <c:if test="${tweets.hasPrevious()}">
                            <li><a href='<c:url value="?page=${tweets.previousPageable().pageNumber + 1}"/>'>Previous</a></li>
                        </c:if>
                        <c:if test="${tweets.hasNext()}">
                            <li><a href='<c:url value="?page=${tweets.nextPageable().pageNumber + 1}"/>'>Next</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
    </jsp:body>
</t:pageWrapper>