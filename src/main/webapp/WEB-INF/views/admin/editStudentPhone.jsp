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
                <th style="width: 40%;"><h4>${submessage2}</h4></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh" aria-hidden="true"></i></a>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Phone Numbers</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <c:forEach var="phoneNumber" items="${phoneNumbers}" varStatus="loop">
                        <li><a onclick="getCategoryIndex(${loop.index})" href="#">${phoneNumber}</a></li>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">

                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <a class="btn btn-primary" type="button" href="/admin/students/ST100003/edit-guardian">${buttonmessage}</button>
                </td>
            </tr>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
