<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Ashish Kumar">
    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/carousel.css" rel="stylesheet">
</head>

<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Carousel</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse ml-2" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active mr-2">
                            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="nav-item mr-2">
                                <a class="nav-link" href="/admin">Admin</a>
                            </li>
                        </sec:authorize>
                        <li class="nav-item mr-2">
                            <a class="nav-link" href="#">Link</a>
                        </li>
                        <li class="nav-item mr-2">
                            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                        </li>
                        <li class="nav-item dropdown mr-2">
                            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">Dropdown</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown01">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </li>
                    </ul>
                    <form class="form-inline mt-2 mt-md-0">
                        <c:choose>
                            <c:when test="${not empty pageContext.request.userPrincipal}">
                                <ul class="navbar-nav mr-2">
                                    <li class="nav-item dropdown mr-2">
                                        <a class="nav-link dropdown-toggle" href="#" id="userdropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            User: <c:out value="${pageContext.request.userPrincipal.name}" />
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="userdropdown">
                                            <a class="dropdown-item" href="#">View Profile</a>
                                            <a class="dropdown-item" href="/logout">Logout</a>
                                        </div>
                                    </li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <ul class="navbar-nav mr-4">
                                    <li class="nav-item mr-2">
                                        <a class="nav-link" href="/login">Login</a>
                                    </li>
                                    <li class="nav-item mr-2">
                                        <a class="nav-link" href="/register">Register</a>
                                    </li>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    </header>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb" style="margin-bottom: 0;">
        </ol>
    </nav>

    <div class="jumbotron">
        <div class="container">
            <h4>${title}</h4>
            ${message}
        </div>
    </div>
