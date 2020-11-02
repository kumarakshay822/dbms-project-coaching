<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container">
    <ul>
        <li><a href="/${role}/users">All Users</a></li>
        <li><a href="/${role}/academics">Academic Portal</a></li>
        <li><a href="/${role}/students">Student Portal</a></li>
        <li><a href="/${role}/staffs">Staff Portal</a></li>
        <li><a href="/${role}/teachers">Teacher Portal</a></li>
        <li><a href="/${role}/payroll">Payroll Management</a></li>
        <li><a href="/${role}/attendance">Attendance Management</a></li>
        <li><a href="/${role}/complaints">Complaints Portal</a></li>
        <li><a href="/${role}/feedbacks">Feedback Portal</a></li>
    </ul>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
