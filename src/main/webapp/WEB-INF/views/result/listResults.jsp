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
        <a class="btn btn-primary" href="/admin/academics/tests/${testId}/results/add" role="button" >Add Result</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Total Marks</th>
                    <th>Rank</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${results}" var="result">
                <tr>
                    <td>ST${result.student.studentId}</td>
                    <td>${result.student.user.firstName} ${result.student.user.middleName} ${result.student.user.lastName}</td>
                    <td>${result.marksScored}</td>
                    <td>${marksToRank[result.marksScored]}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/academics/tests/${testId}/results/ST${result.student.studentId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/tests/${testId}/results/ST${result.student.studentId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/tests/${testId}/results/ST${result.student.studentId}/delete',
                        'Do you want to delete this Result? \nWarning! This action is destructible!')"
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
