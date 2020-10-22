<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/students/add" role="button">Add Student</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email Address</th>
                    <th>Name</th>
                    <th>Date Created</th>
                    <th>Last Login</th>
                    <th>Role</th>
                    <th>Activated?</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.emailAddress}</td>
                    <td>${user.firstName} ${user.middleName} ${user.lastName}</td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${user.dateCreated}" />
                    </td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${user.lastLoginDate}" />
                        <fmt:formatDate pattern="HH:mm:ss" value="${user.lastLoginTime}" />
                    </td>
                    <td>${user.role}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.isActive == true}">Yes</c:when>
                            <c:otherwise><a class="btn btn-outline-success btn-sm"
                                    onclick="getRequest('/admin/users/${user.userId}/activate')"
                                    role="button">Activate</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/users/${user.userId}/delete',
                                            'Do you want to delete this User? \nWarning! This action is destructible!')"
                        role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
