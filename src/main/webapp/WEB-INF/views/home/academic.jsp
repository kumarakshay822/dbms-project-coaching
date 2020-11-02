<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container">
    <ul>
        <li><a href="/${role}/academics/subjects">Subjects</a></li>
        <li><a href="/${role}/academics/courses">Courses</a></li>
        <li><a href="/${role}/academics/batches">Batches</a></li>
        <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_STAFF')">
        <li><a href="/${role}/academics/batches-assigned">Batches Assigned</a></li>
        </sec:authorize>
        <li><a href="/${role}/academics/tests">Tests and Result Portal</a></li>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')">
        <li><a href="/${role}/academics/enrollments">Enrollment Portal</a></li>
        </sec:authorize>
    </ul>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
