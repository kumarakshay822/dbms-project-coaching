<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/academics/tests/add" role="button" >Add Test</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Test ID</th>
                    <th>Test Name</th>
                    <th>Course</th>
                    <th>Room Number</th>
                    <th>Test Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Maximum Marks</th>
                    <th>Difficulty</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${tests}" var="test">
                <tr>
                    <td>${test.testId}</td>
                    <td>${test.testName}</td>
                    <td>${test.course.courseName} - ${test.course.courseId}</td>
                    <td>${test.roomNumber}</td>
                    <td>${test.testDate}</td>
                    <td>${test.startTime}</td>
                    <td>${test.endTime}</td>
                    <td>${test.maximumMarks}</td>
                    <td>${test.difficulty}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/academics/tests/${test.testId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/tests/${test.testId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/tests/${test.testId}/delete',
                        'Do you want to delete this Test? \nWarning! This action is destructible!')"
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
