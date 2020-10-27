<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="row justify-content-center mb-3">
        <h2>${submessage1}</h2>
    </div>
    <c:if test="${normal == true}">
    <div class="div text-right">
        <select id="courseSelect">
            <c:forEach var="course" items="${courses}">
                <option value="${course.courseId}">
                    ${course.courseId} - ${course.courseName}
                </option>
            </c:forEach>
        </select>
        <select id="batchSelect">
            <option value="${course.courseId}">
                ${course.courseId} - ${course.courseName}
            </option>
        </select>
        <button id="enrollment" class="btn btn-outline-success btn-sm ml-3">Add Enrollment</a>
    </div>
    </c:if>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Enrollment ID</th>
                    <th>Student ID</th>
                    <th>Course ID</th>
                    <th>Batch ID</th>
                    <th>Join Date</th>
                    <th>End Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${enrollments}" var="enrollment">
                <tr>
                    <td>${enrollment.enrollmentId}</td>
                    <td><a href="/admin/students/ST${enrollment.studentId}">ST${enrollment.studentId}</a></td>
                    <td><a href="/admin/academics/courses/${enrollment.courseId}">${enrollment.courseId}</a></td>
                    <td><a href="/admin/academics/courses/${enrollment.courseId}/${enrollment.batchId}">${enrollment.batchId}</a></td>
                    <td>${enrollment.joinDate}</td>
                    <td>${enrollment.endDate}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/academics/enrollments/${enrollment.enrollmentId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/academics/enrollments/${enrollment.enrollmentId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/academics/enrollments/${enrollment.enrollmentId}/delete',
                        'Do you want to delete this Enrollment? \nWarning! This action is destructible!')"
                            role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script src="/js/jquery-3.5.1.min.js"></script>
<script>
    $("#enrollment").click(function () {
        location.href = "/admin/academics/courses/" + $('#courseSelect').val() + "/" + $('#batchSelect').val() + "/enrollments/add";
    });
    function courseSelectOnChange() {
        var courseId = $("#courseSelect").val();
        var data = {};
        var url = "/admin/academics/courses/" + courseId + "/get-all-batches";
        $.ajax({
            url: url,
            type: "post",
            data: data,
            success: function (data, status, xhr) {
                $('#batchSelect').find('option').remove();
                for (var i = 0; i < data.length; i++) {
                    $('#batchSelect').append('<option value = "' + data[i].batchId + '">' + data[i].batchId + ' - ' + data[i].batchName + '</option>');
                }
            },
            error: function (xhr, status, err) {
                alert(status);
            }
        });
    }
    $(function () {
        courseSelectOnChange();
        $("#courseSelect").change(courseSelectOnChange);
    });


</script>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
