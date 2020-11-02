<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div" style="text-align: center;">
        Result for Test Id: ${testId}
    </div>
    <div class="div text-right">
        <a class="btn btn-outline-primary btn-sm" href="/${role}/academics/tests/${testId}/results" role="button" >View all Results</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Total Marks</th>
                    <th>Recheck Comments</th>
                    <th>New Marks</th>
                </tr>
            </thead>
            <c:forEach items="${results}" var="result">
                <tr>
                    <td>ST${result.student.studentId}</td>
                    <td>${result.student.user.firstName} ${result.student.user.middleName} ${result.student.user.lastName}</td>
                    <td>${result.marksScored}</td>
                    <td>${result.recheckComments}</td>
                    <td>
                        <c:choose>
                            <c:when test="${result.isDoneRecheck == true}">
                                Recheck Done
                            </c:when>
                            <c:otherwise>
                                <input type="number" id="newMarks">
                                <a class="btn btn-outline-success btn-sm" onclick="postRequest('/${role}/academics/tests/${result.testId}/results-recheck/${result.student.studentId}',
                                                    {'newMarks': $('#newMarks').val()})" role="button">Mark Done</a>
                                <div id="error" style="color: red;"></div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
