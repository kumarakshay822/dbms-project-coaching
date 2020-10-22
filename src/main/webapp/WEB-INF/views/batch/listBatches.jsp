<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/academics/batches/add" role="button" >Add Batch</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Batch ID</th>
                    <th>Batch Name</th>
                    <th>Course</th>
                    <th>Room Number</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${batches}" var="batch">
                <tr>
                    <td>${batch.batchId}</td>
                    <td>${batch.batchName}</td>
                    <td>${batch.course.courseName} - ${batch.course.courseId}</td>
                    <td>${batch.roomNumber}</td>
                    <td>${batch.startTime}</td>
                    <td>${batch.endTime}</td>
                    <td>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/batches/${batch.course.courseId}-${batch.batchId}/edit" role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/batches/${batch.course.courseId}-${batch.batchId}/delete',
                        'Do you want to delete this Batch? \nWarning! This action is destructible!')"
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
