<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-right">
        <a class="btn btn-primary" href="/admin/payroll/add" role="button" >Add Payroll</a>
    </div>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Payment Ref No</th>
                    <th>Employee</th>
                    <th>Month</th>
                    <th>Year</th>
                    <th>Salary credited</th>
                    <th>Date credited</th>
                    <th>Action</th>
                </tr>
            </thead>
            <c:forEach items="${payrolls}" var="payroll">
                <tr>
                    <td><a href="/admin/payroll/${payroll.paymentRefNo}">${payroll.paymentRefNo}</a></td>
                    <td>
                        <c:if test="${payroll.employee.user.role == 'ROLE_TEACHER'}">
                            <a href="/admin/teachers/ET${payroll.employee.employeeId}">
                                ET${payroll.employee.employeeId} - ${payroll.employee.user.firstName} ${payroll.employee.user.middleName}
                                ${payroll.employee.user.lastName}
                            </a>
                        </c:if>
                        <c:if test="${payroll.employee.user.role == 'ROLE_STAFF'}">
                            <a href="/admin/staffs/ES${payroll.employee.employeeId}">
                                ES${payroll.employee.employeeId} - ${payroll.employee.user.firstName} ${payroll.employee.user.middleName}
                                ${payroll.employee.user.lastName}
                            </a>
                        </c:if>
                    </td>
                    <td>${payroll.month}</td>
                    <td>${payroll.year}</td>
                    <td>${payroll.salaryCredited}</td>
                    <td>${payroll.dateCredited}</td>
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/admin/payroll/${payroll.paymentRefNo}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/admin/payroll/${payroll.paymentRefNo}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/admin/payroll/${payroll.paymentRefNo}/delete',
                        'Do you want to delete this Payroll? \nWarning! This action is destructible!')"
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
