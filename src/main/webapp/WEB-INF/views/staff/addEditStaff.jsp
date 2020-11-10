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
            <form:form class="form-horizontal" action="${submiturl}" method="post" modelAttribute="staff">
                <tr>
                    <th style="width: 40%;">
                        <h4>${submessage2}</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                <c:if test="${edit == true}">
                <tr>
                    <th style="width: 40%; text-align: center;">Employee ID</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">ES${staff.employee.employeeId}</td>
                </tr>
                </c:if>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <th style="width: 40%; text-align: center;">Name</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.user.firstName" class="form-control" required="true"></form:input>
                        <form:errors path="employee.user.firstName" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.user.middleName" class="form-control"></form:input>
                        <form:errors path="employee.user.middleName" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.user.lastName" class="form-control"></form:input>
                        <form:errors path="employee.user.lastName" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Username</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.user.username" class="form-control" required="true"></form:input>
                        <form:errors path="employee.user.username" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Email Address</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="email" path="employee.user.emailAddress" class="form-control" required="true"></form:input>
                        <form:errors path="employee.user.emailAddress" style="color: red;"></form:errors>
                    </td>
                </tr>
                </sec:authorize>
                <tr>
                    <th style="width: 40%; text-align: center;">Gender</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:select class="form-control" path="gender" required="true">
                            <form:option value="Male">Male</form:option>
                            <form:option value="Female">Female</form:option>
                            <form:option value="Not Specified">Not Specified</form:option>
                        </form:select>
                        <form:errors path="gender" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Date of Birth</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="dateOfBirth" class="form-control" required="true"></form:input>
                        <form:errors path="dateOfBirth" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Address</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="houseNumber" class="form-control"></form:input>
                        <form:errors path="houseNumber" style="color: red;"></form:errors>
                        <form:input type="text" path="street" class="form-control" required="true"></form:input>
                        <form:errors path="street" style="color: red;"></form:errors>
                        <form:input type="text" path="city" class="form-control" required="true"></form:input>
                        <form:errors path="city" style="color: red;"></form:errors>
                        <form:input type="text" path="state" class="form-control" required="true"></form:input>
                        <form:errors path="state" style="color: red;"></form:errors>
                        <form:input type="number" path="pinCode" class="form-control" required="true"></form:input>
                        <form:errors path="pinCode" style="color: red;"></form:errors>
                    </td>
                </tr>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <th style="width: 40%; text-align: center;">Basic Salary</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="number" path="employee.basicSalary" class="form-control"></form:input>
                        <form:errors path="employee.basicSalary" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Join Date</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="employee.joinDate" class="form-control"></form:input>
                        <form:errors path="employee.joinDate" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">End Date</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="employee.endDate" class="form-control"></form:input>
                        <form:errors path="employee.endDate" style="color: red;"></form:errors>
                    </td>
                </tr>
                </sec:authorize>
                <tr>
                    <th style="width: 40%; text-align: center;">PAN Number</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.panNumber" class="form-control" required="true"></form:input>
                        <form:errors path="employee.panNumber" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Account Number</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.accountNumber" class="form-control" required="true"></form:input>
                        <form:errors path="employee.accountNumber" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Bank Name</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.bankName" class="form-control" required="true"></form:input>
                        <form:errors path="employee.bankName" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Bank Branch</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.bankBranch" class="form-control" required="true"></form:input>
                        <form:errors path="employee.bankBranch" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">IFSC Code</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="employee.ifscCode" class="form-control" required="true"></form:input>
                        <form:errors path="employee.ifscCode" style="color: red;"></form:errors>
                    </td>
                </tr>
                <c:if test="${edit == true}">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <th style="width: 40%; text-align: center;">Activated?</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:select class="form-control" path="employee.user.isActive" required="true">
                            <form:option value="1">Yes</form:option>
                            <form:option value="0">No</form:option>
                        </form:select>
                        <form:errors path="employee.user.isActive" style="color: red;"></form:errors>
                    </td>
                </tr>
                </sec:authorize>
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
