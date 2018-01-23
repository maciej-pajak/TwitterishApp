<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav" id="side-menu">
	<li>
	    <a href='<c:url value="/"/>'><i class="fa fa-twitter-square fa-fw"></i> Latest tweets</a>
	</li>
	<li>
	    <a href='<c:url value="/register"/>'><i class="fa fa-user fa-fw"></i> Sign up</a>
	</li>
	<li>
	    <a href='<c:url value="/login"/>'><i class="fa fa-sign-in fa-fw"></i> Log in</a>
	</li>
</ul>