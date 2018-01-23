<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageWrapper title="Tweet">

    <jsp:attribute name="navigation">
        <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <a href='<c:url value="/tweet/${tweet.id}"/>'>
                        <b>${tweet.user.username}</b>
                    </a> tweeted on ${dateFormatter.format(tweet.created)}
                </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h4>${tweet.text}</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Comments
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-yellow">
                            <div class="panel-body">
                                <form method="post" action='<c:url value="/tweet/${tweet.id}/comment"/>'>
                                    <div>
                                        <textarea rows="4" cols="50" name="comment" required="required" placeholder="type your comment here"></textarea>
                                    </div>
                                    <div>
                                        <input type="submit" class="btn btn-warning" value="Comment!">
                                    </div> 
                                </form>
                            </div>
                        </div>
                        <c:forEach var="c" items="${comments.getContent()}">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <a href='<c:url value="/user/${tweet.user.id}"/>'>
                                        <b>${c.user.username}</b>
                                    </a> commented on ${dateFormatter.format(c.created)}
                                </div>
                                <div class="panel-body">
                                    <p>${c.comment}</p>
                                </div>
                            </div>
                        </c:forEach>
                        <ul class="pager">
                            <c:if test="${comments.hasPrevious()}">
                                <li><a href='<c:url value="?page=${comments.previousPageable().pageNumber + 1}"/>'>See newer comments</a></li>
                            </c:if>
                            <c:if test="${comments.hasNext()}">
                                <li><a href='<c:url value="?page=${comments.nextPageable().pageNumber + 1}"/>'>See older comments</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pageWrapper>