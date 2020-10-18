<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<nav aria-label="breadcrumb">
    <ol class="breadcrumb" style="margin-bottom: 0;">
        <li class="breadcrumb-item active" aria-current="page">Admin</li>
    </ol>
</nav>

<div class="jumbotron">
    <div class="container">
        <h4>${title}</h4>
        ${message}
    </div>
</div>

<div class="container">
    <ul>
        <li><a href="/admin/users">All Users</a></li>
        <li><a href="/admin/students">Student Portal</a></li>
        <li><a href="/admin/staffs">Staff Portal</a></li>
        <li><a href="/admin/teachers">Teacher Portal</a></li>
    </ul>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
