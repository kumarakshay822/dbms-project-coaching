<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<nav aria-label="breadcrumb">
    <ol class="breadcrumb" style="margin-bottom: 0;">
        <li class="breadcrumb-item active"><a href="/admin">Admin</a></li>
        <li class="breadcrumb-item active"><a href="/admin/students">Students Portal</a></li>
        <li class="breadcrumb-item active" aria-current="page">ST${student.studentId}</li>
    </ol>
</nav>

<div class="jumbotron">
    <div class="container">
        <h4>${title}</h4>
        ${message}
    </div>
</div>


<%@ include file="/WEB-INF/views/template/footer.jsp" %>
