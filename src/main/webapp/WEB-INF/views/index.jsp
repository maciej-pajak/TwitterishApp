<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageWrapper title="Homepage">

    <jsp:attribute name="navigation">
        
        
        <c:choose>
            <c:when test="${logged}">
                <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
            </c:when>    
            <c:otherwise>
                <%@ include file="/WEB-INF/fragments/notLoggedInNav.jsp" %>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Latest tweets</h1>
            </div>
        </div>
        <c:if test="${logged}">
            <div class="row">
                <div class="col-lg-12">
                    <div class="alert alert-info">
                        <form action='<c:url value="/tweet/new"/>' method="post">
                            <div class="form-group">
                                <label>Tweet something to the world</label>
                                <textarea class="form-control" rows="3" name="tweet"></textarea>
                            </div>
                            <div>
                                <button type="submit" class="btn btn-primary">Tweet!</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row">
                <div class="col-lg-12">
                    <c:forEach items="${tweets.getContent()}" var="tweet">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <a href='<c:url value="/user/${tweet.user.id}"/>'>
                                    <b>${tweet.user.username}</b>
                                </a> tweeted on ${dateFormatter.format(tweet.created)}
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
                            <li><a href='<c:url value="/?page=${tweets.previousPageable().pageNumber + 1}"/>'>Previous</a></li>
                        </c:if>
                        <c:if test="${tweets.hasNext()}">
                            <li><a href='<c:url value="/?page=${tweets.nextPageable().pageNumber + 1}"/>'>Next</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
    </jsp:body>
</t:pageWrapper>