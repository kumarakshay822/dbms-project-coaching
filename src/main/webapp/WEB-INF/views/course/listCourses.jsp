<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/academics/courses/add" role="button" >Add Course</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Fee</th>
                    <th>Subjects</th>
                    <th>Add Subject</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.courseId}</td>
                    <td>${course.name}</td>
                    <td>${course.description}</td>
                    <td>${course.fee}</td>
                    <td>
                        <c:forEach var="subject" items="${course.subjects}">
                            <div>${subject.name} - ${subject.subjectId}</div>
                        </c:forEach>
                    </td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/academics/courses/${course.courseId}/add-subject"
                            role="button">Add Subject</a>
                    </td>
                    <td>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/courses/${course.courseId}/edit" role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/courses/${course.courseId}/delete',
                        'Do you want to delete this Course? \nWarning! This action is destructible!')"
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
