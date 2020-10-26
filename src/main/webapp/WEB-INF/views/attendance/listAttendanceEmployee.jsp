<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <button class="btn btn-outline-success btn-sm ml-5" onclick="location.href='/admin/attendance/'">Get All Attendance</a>
    </div>
    <div class="div text-right mt-2">
        <a class="btn btn-primary" href="/admin/attendance/add" role="button" >Add Attendance</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Employee</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Remarks</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${attendances}" var="attendance">
                <tr>
                    <td>
                        <c:if test="${attendance.employee.user.role == 'ROLE_TEACHER'}">ET${attendance.employee.employeeId}</c:if>
                        <c:if test="${attendance.employee.user.role == 'ROLE_STAFF'}">ES${attendance.employee.employeeId}</c:if>
                        - ${attendance.employee.user.firstName} ${attendance.employee.user.middleName} ${attendance.employee.user.lastName}
                    </td>
                    <td>${attendance.date}</td>
                    <td>
                        <c:if test="${attendance.isPresent == true}">Present</c:if>
                        <c:if test="${attendance.isPresent == false}"><span style="color: red;">Absent</span></c:if>
                    </td>
                    <td>${attendance.remarks}</td>
                    <td>
                        <c:if test="${attendance.employee.user.role == 'ROLE_TEACHER'}">
                            <a class="btn btn-outline-primary btn-sm" href="/admin/attendance/${attendance.date}/ET${attendance.employee.employeeId}/edit" role="button">Edit</a>
                        </c:if>
                        <c:if test="${attendance.employee.user.role == 'ROLE_STAFF'}">
                            <a class="btn btn-outline-primary btn-sm" href="/admin/attendance/${attendance.date}/ES${attendance.employee.employeeId}/edit" role="button">Edit</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
