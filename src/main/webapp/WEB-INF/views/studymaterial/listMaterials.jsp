<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/academics/subjects/${subjectId}/materials/add" role="button" >Add Study Material</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Subject ID</th>
                    <th>Material ID</th>
                    <th>Topic Name</th>
                    <th>Difficulty</th>
                    <th>Description</th>
                    <th>File</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${materials}" var="material" varStatus="loop">
                <tr>
                    <td>${material.subjectId}</td>
                    <td>${material.materialId}</td>
                    <td>${material.topicName}</td>
                    <td>${material.difficulty}</td>
                    <td>${material.description}</td>
                    <td><a href="/${urls[loop.count-1]}" target="_blank">${material.filename}</a></td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/academics/subjects/${material.subjectId}/materials/${material.materialId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/subjects/${material.subjectId}/materials/${material.materialId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/subjects/${material.subjectId}/materials/${material.materialId}/delete',
                        'Do you want to delete this Study Material? \nWarning! This action is destructible!')"
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
