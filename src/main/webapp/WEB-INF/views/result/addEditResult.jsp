<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <th style="width: 60%;">
                    <h4>${submessage2}</h4>
                </th>
                <td style="width: 40%; text-align: right;">
                    <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                            aria-hidden="true"></i></a>
                </td>
            </tr>
        </table>
        <table class="table table-borderless">
            <form:form class="form-horizontal" action="${submiturl}"
                method="post" modelAttribute="result">
                <tr>
                    <th style="width: 40%; text-align: center;">Test ID</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">${testId}</td>
                </tr>
                <c:choose>
                    <c:when test="${edit == true}">
                        <tr>
                            <th style="width: 40%; text-align: center;">Student ID</th>
                            <th style="width: 10%;"></th>
                            <td style="width: 50%">ST${result.student.studentId}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%; text-align: center;">Student Name</th>
                            <th style="width: 10%;"></th>
                            <td style="width: 50%">${result.student.user.firstName} ${result.student.user.middleName} ${result.student.user.lastName}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th style="width: 40%; text-align: center;">Student</th>
                            <th style="width: 10%;"></th>
                            <td style="width: 50%">
                                <form:select class="form-control" path="student.studentId" required="true">
                                    <c:forEach var="student" items="${students}">
                                        <form:option value="${student.studentId}">
                                            ST${student.studentId} - ${student.user.firstName} ${student.user.middleName} ${student.user.lastName}
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <th style="width: 40%; text-align: center;">Marks Scored</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="number" path="marksScored" class="form-control" required="true"></form:input>
                        <form:errors path="marksScored" style="color: red;"></form:errors>
                    </td>
                </tr>
                <c:if test="${edit == true}">
                    <tr>
                        <th style="width: 40%; text-align: center;">Applied for Recheck?</th>
                        <th style="width: 10%;"></th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="hasAppliedRecheck">
                                <form:option value="1">Yes</form:option>
                                <form:option value="0">No</form:option>
                            </form:select>
                            <form:errors path="hasAppliedRecheck" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%; text-align: center;">Is Recheck Done?</th>
                        <th style="width: 10%;"></th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="isDoneRecheck">
                                <form:option value="1">Yes</form:option>
                                <form:option value="0">No</form:option>
                            </form:select>
                            <form:errors path="isDoneRecheck" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%; text-align: center;">Recheck Comments</th>
                        <th style="width: 10%;"></th>
                        <td style="width: 50%">
                            <form:input type="text" path="recheckComments" class="form-control"></form:input>
                            <form:errors path="recheckComments" style="color: red;"></form:errors>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit">${buttonmessage}</button>
                    </td>
                </tr>
            </form:form>

        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
