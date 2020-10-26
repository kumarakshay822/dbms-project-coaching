<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <input type="date" id="date">
        <button class="btn btn-outline-success btn-sm ml-5" onclick="location.href='/admin/attendance?date=' + $('#date').val()">Filter</a>
    </div>
    <div class="div text-right mt-2">
        <span class="mr-4">Total Days: ${days}</span>
        <a class="btn btn-primary" href="/admin/attendance/add" role="button">Add Attendance</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Employee</th>
                    <th>Days Present</th>
                    <th>Days Absent</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${attendances}" var="attendance">
                <tr>
                    <td>
                        <c:if test="${attendance.role == 'ROLE_TEACHER'}">
                            <a href="/admin/teachers/ET${attendance.employeeId}">ET${attendance.employeeId} -
                                ${attendance.name}</a>
                        </c:if>
                        <c:if test="${attendance.role == 'ROLE_STAFF'}">
                            <a href="/admin/staffs/ES${attendance.employeeId}">ES${attendance.employeeId} -
                                ${attendance.name}</a>
                        </c:if>
                    </td>
                    <td>${attendance.daysPresent}</td>
                    <td><span style="color: red">${days - attendance.daysPresent}</span></td>
                    <td>
                        <c:if test="${attendance.role == 'ROLE_TEACHER'}">
                            <a class="btn btn-outline-success btn-sm"
                                href="/admin/attendance/ET${attendance.employeeId}" role="button">View</a>
                        </c:if>
                        <c:if test="${attendance.role == 'ROLE_STAFF'}">
                            <a class="btn btn-outline-success btn-sm"
                                href="/admin/attendance/ES${attendance.employeeId}" role="button">View</a>
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
