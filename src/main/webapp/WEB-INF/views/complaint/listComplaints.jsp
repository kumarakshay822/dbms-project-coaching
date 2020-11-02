<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/${role}/complaints/add" role="button" >Add Complaint</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Complaint ID</th>
                    <th>Date & Time</th>
                    <th>Student ID</th>
                    <th>Subject</th>
                    <th>Description</th>
                    <th>Response</th>
                    <th>Resolved?</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${complaints}" var="complaint">
                <tr>
                    <td>${complaint.complaintId}</td>
                    <td>${complaint.date} ${complaint.time}</td>
                    <td><a href="/${role}/students/ST${complaint.studentId}">ST${complaint.studentId}</a></td>
                    <td>${complaint.subject}</td>
                    <td>${complaint.description}</td>
                    <td>${complaint.response}</td>
                    <td>
                        <c:choose>
                            <c:when test="${complaint.isResolved == true}">Yes</c:when>
                            <c:otherwise>
                                <span style="color: red;">No</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/${role}/complaints/${complaint.complaintId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/${role}/complaints/${complaint.complaintId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/${role}/complaints/${complaint.complaintId}/delete',
                        'Do you want to delete this Complaint? \nWarning! This action is destructible!')"
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
