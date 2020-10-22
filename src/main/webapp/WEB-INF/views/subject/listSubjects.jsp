<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/academics/subjects/add" role="button" >Add Subject</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Subject ID</th>
                    <th>Subject Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <td>${subject.subjectId}</td>
                    <td>${subject.subjectName}</td>
                    <td>${subject.description}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/academics/subjects/${subject.subjectId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/subjects/${subject.subjectId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/subjects/${subject.subjectId}/delete',
                        'Do you want to delete this Subject? \nWarning! This action is destructible!')"
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
