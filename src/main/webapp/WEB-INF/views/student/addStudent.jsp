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
            <form:form class="form-horizontal" action="/admin/students/add"
                method="post" modelAttribute="student">
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
                <tr>
                    <th style="width: 40%; text-align: center;">Name</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="user.firstName" class="form-control" required="true"></form:input>
                        <form:errors path="user.firstName" style="color: red;"></form:errors>
                        <form:input type="text" path="user.middleName" class="form-control"></form:input>
                        <form:errors path="user.middleName" style="color: red;"></form:errors>
                        <form:input type="text" path="user.lastName" class="form-control"></form:input>
                        <form:errors path="user.lastName" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Username</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="user.username" class="form-control" required="true"></form:input>
                        <form:errors path="user.username" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Email Address</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="email" path="user.emailAddress" class="form-control" required="true"></form:input>
                        <form:errors path="user.emailAddress" style="color: red;"></form:errors>
                    </td>
                </tr>
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
                <tr>
                    <th style="width: 40%; text-align: center;">School Attending</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="schoolAttending" class="form-control" required="true"></form:input>
                        <form:errors path="schoolAttending" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">10th Percentage</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="percentage10th" class="form-control" required="true"></form:input>
                        <form:errors path="percentage10th" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">12th Percentage</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="percentage12th" class="form-control" required="true"></form:input>
                        <form:errors path="percentage12th" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Activated?</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:select class="form-control" path="user.isActive" required="true">
                            <form:option value="1">Yes</form:option>
                            <form:option value="0">No</form:option>
                        </form:select>
                        <form:errors path="isActive" style="color: red;"></form:errors>
                    </td>
                </tr>
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
