<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav" id="side-menu">
	<li>
	    <a href='<c:url value="/"/>'><i class="fa fa-twitter-square fa-fw"></i> Latest tweets</a>
	</li>
    <li>
        <a href='<c:url value="/user/inbox"/>'><i class="fa fa-envelope fa-fw"></i>
            <c:choose>
                <c:when test = "${unreadCount gt 0}">
                    <b> Inbox (${unreadCount} unread)</b>
                </c:when>
                <c:otherwise>
                    <span> Inbox</span>
                </c:otherwise>
            </c:choose>
        </a>
    </li>
    <li>
        <a href='<c:url value="/user/outbox"/>'><i class="fa fa-send fa-fw"></i> Outbox</a>
    </li>
	<li>
	    <a href='<c:url value="/user/${loggedUser.id}"/>'><i class="fa fa-twitter fa-fw"></i> My tweets</a>
	</li>
	<li>
	    <a href='<c:url value="/user/edit"/>'><i class="fa fa-edit fa-fw"></i> Edit profile</a>
	</li>
    <li>
        <a href='<c:url value="/logout"/>'><i class="fa fa-sign-out fa-fw"></i> Log out</a>
    </li>
</ul>