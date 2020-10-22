<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
    <div class="row justify-content-center mb-3">
        <h2>${submessage1}</h2>
    </div>
    <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <a class="btn btn-primary" href="/admin/academics/courses/${batch.course.courseId}/${batch.batchId}/edit" role="button">Edit Batch</a>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Batch ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.batchId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Batch Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.batchName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.course.courseName} - ${batch.course.courseId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Room Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.roomNumber}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Start Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.startTime}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">End Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.endTime}</td>
            </tr>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
