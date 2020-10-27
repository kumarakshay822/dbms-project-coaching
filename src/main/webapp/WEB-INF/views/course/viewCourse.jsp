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
                    <a class="btn btn-primary" href="/admin/academics/courses/${course.courseId}/enrollments" role="button">View
                        Students</a>
                    <a class="btn btn-primary" href="/admin/academics/courses/${course.courseId}/add-subject" role="button">
                        Add / Edit Subject</a>
                    <a class="btn btn-primary" href="/admin/academics/courses/${course.courseId}/edit" role="button">Edit Course</a>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.courseId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.courseName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Description</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.description}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Subjects</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <c:forEach var="subject" items="${subjects}">
                        <div>${subject.subjectName} - ${subject.subjectId}</div>
                    </c:forEach>
                </td>
            </tr>
        </table>
        <div class="col-12 mt-4" style="text-align: center;">
            <hr>
            <h5>Batches</h5>
        </div>
        <div class="col-12" style="text-align: right;">
            <a class="btn btn-primary" href="/admin/academics/courses/${course.courseId}/add-batch" role="button">Add Batch</a>
        </div>
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Batch ID</th>
                    <th>Batch Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${batches}" var="batch">
                <tr>
                    <td>${batch.batchId}</td>
                    <td>${batch.batchName}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm"
                            href="/admin/academics/courses/${batch.course.courseId}/${batch.batchId}" role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/courses/${batch.course.courseId}/${batch.batchId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm"
                            onclick="getRequestWithConfirmation('/admin/academics/courses/${batch.course.courseId}/${batch.batchId}/delete',
                                'Do you want to delete this Batch? \nWarning! This action is destructible!')" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
