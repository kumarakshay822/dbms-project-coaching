<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <sec:authorize access='hasRole("ROLE_STUDENT")'>
    <div class="div text-right">
        <a class="btn btn-primary" href="/${role}/complaints/add" role="button" >Add Complaint</a>
    </div>
    </sec:authorize>
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
                    <td>
                        <c:choose>
                            <c:when test="${complaint.isResolved == true}">${complaint.response}</c:when>
                            <c:when test="${complaint.isResolved == false && role != 'admin'}">${complaint.response}</c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" id="response" value="${complaint.response}"></input>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${complaint.isResolved == true}">Yes</c:when>
                            <c:when test="${complaint.isResolved == false && role != 'admin'}"><span style="color: red;">No</span></c:when>
                            <c:otherwise>
                                <a class="btn btn-outline-success btn-sm" onclick="postRequest('/${role}/complaints/${complaint.complaintId}/resolve',
                                                                    {'response': $('#response').val()})" role="button">Resolve</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/${role}/complaints/${complaint.complaintId}"
                            role="button">View</a>
                        <c:if test="${role == 'admin' || (complaint.isResolved == false && role == 'student')}">
                        <a class="btn btn-outline-primary btn-sm" href="/${role}/complaints/${complaint.complaintId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/${role}/complaints/${complaint.complaintId}/delete',
                        'Do you want to delete this Complaint? \nWarning! This action is destructible!')"
                            role="button">Delete</a>
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
