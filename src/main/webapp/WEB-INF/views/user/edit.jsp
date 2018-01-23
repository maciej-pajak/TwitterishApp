<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
                        <form method="post" action='<c:url value="/user/edit"/>'>
                            <div class="form-group">
                                <label>New email:</label>
                                <input class="form-control" type="email" name="email" placeholder="enter new email">
                            </div>
                            <div>
                                <input type="submit" class="btn btn-primary" value="Update">
                            </div> 
                        </form>
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
                        <form method="post" action='<c:url value="/user/delete"/>'>
                            <div class="form-group">
                                <label>Enter your password to confirm:</label>
                                <input class="form-control" type="password" name="password" placeholder="confirm action with your password">
                            </div>
                            <div>
                                <input type="submit" class="btn btn-danger" value="DELETE MY ACCOUNT">
                            </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pageWrapper>