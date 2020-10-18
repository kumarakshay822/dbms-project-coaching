<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<nav aria-label="breadcrumb">
    <ol class="breadcrumb" style="margin-bottom: 0;">
        <li class="breadcrumb-item active"><a href="/admin">Admin</a></li>
        <li class="breadcrumb-item active" aria-current="page">Students Portal</li>
    </ol>
</nav>

<div class="jumbotron">
    <div class="container">
        <h4>${title}</h4>
        ${message}
    </div>
</div>

<div class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Username</th>
                <th>Email Address</th>
                <th>Date Created</th>
                <th>Activated?</th>
            </tr>
        </thead>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.studentId}</td>
                <td>${student.user.firstName} ${student.user.middleName} ${student.user.lastName}</td>
                <td>${student.gender}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${student.dateOfBirth}" /></td>
                <td>${student.user.username}</td>
                <td>${student.user.emailAddress}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${student.user.dateCreated}" /></td>
                <td>${student.user.isActive}</td>
            </tr>
        </c:forEach>
        <tbody>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
