<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/feedbacks/add" role="button" >Add Feedback</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Teacher ID</th>
                    <th>Date & Time</th>
                    <th>Subject</th>
                    <th>Message</th>
                    <th>Response</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${feedbacks}" var="feedback">
                <tr>
                    <td><a href="/admin/students/ST${feedback.studentId}">ST${feedback.studentId}</a></td>
                    <td><a href="/admin/teachers/ET${feedback.employeeId}">ST${feedback.employeeId}</a></td>
                    <td>${feedback.date} ${feedback.time}</td>
                    <td>${feedback.subject}</td>
                    <td>${feedback.message}</td>
                    <td>${feedback.response}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/feedbacks/ST${feedback.studentId}/ET${feedback.employeeId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/feedbacks/ST${feedback.studentId}/ET${feedback.employeeId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/feedbacks/ST${feedback.studentId}/ET${feedback.employeeId}/delete',
                        'Do you want to delete this Feedback? \nWarning! This action is destructible!')"
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
