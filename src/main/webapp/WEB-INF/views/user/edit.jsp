<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:pageWrapper title="Edit profile">

    <jsp:attribute name="navigation">
        <%@ include file="/WEB-INF/fragments/loggedInNav.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Edit profile</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Update profile
                    </div>
                    <div class="panel-body">
                        <form:form method="post" action="${pageContext.request.contextPath}/user/edit" modelAttribute="emailForm">
                            <div class="form-group">
                                <label>Current email:</label>
                                <p class="form-control-static">${loggedUser.email}</p>
                            </div>
                            <div class="form-group">
                                <label>New email:</label>
                                <form:input class="form-control" type="email" path="email" placeholder="enter new email"/>
                            </div> <form:errors path="email"/>
                            <div>
                                <input type="submit" class="btn btn-primary" value="Update">
                            </div> 
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        Delete profile
                    </div>
                    <div class="panel-body">
                        <p>This action will delete your profile, tweets and comments. Cannot be undone.</p>
                        <form:form method="post" action="${pageContext.request.contextPath}/user/delete" modelAttribute="singleStringForm">
                            <div class="form-group">
                                <label>Enter your password to confirm:</label>
                                <form:input class="form-control" type="password" path="string" placeholder="confirm action with your password"/>
                            </div> <form:errors path="string" element="div"/>
                            <div>
                                <input type="submit" class="btn btn-danger" value="DELETE MY ACCOUNT">
                            </div> 
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pageWrapper>