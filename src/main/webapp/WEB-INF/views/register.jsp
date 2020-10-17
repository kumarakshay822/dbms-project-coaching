<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="login-form section text-center py-5">
    <div class="container">
        <div class="row">
            <div class="col-sm-8 offset-sm-2">
                <div class="mt-5">
                    <div class="card card-info">
                        <div class="card-header" style="color: #fff; background-color:#333;">
                            REGISTER
                        </div>
                        <div class="card-body">
                            <form:form class="form-horizontal" action="register" method="post" modelAttribute="userForm">
                                <div class="form-group row">
                                    <label class="col-3 control-label">Username:</label>
                                    <div class="col-9">
                                        <form:input type="text" path="username" class="form-control" placeholder="Enter the username" autofocus="true" required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="username" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Password:</label>
                                    <div class="col-9">
                                        <form:input type="password" path="password" class="form-control" placeholder="Enter the password" required="true"></form:input>
                                        <span toggle="#password-field" class="fa fa-fw fa-eye toggle-password mr-2"
                                            style="float: right; margin-top: -25px; cursor: pointer;"></span>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="password" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Name:</label>
                                    <div class="col-3">
                                        <form:input type="text" path="firstName" class="form-control" placeholder="First name" required="true"></form:input>
                                    </div>
                                    <div class="col-3">
                                        <form:input type="text" path="middleName" class="form-control" placeholder="Middle name"></form:input>
                                    </div>
                                    <div class="col-3">
                                        <form:input type="text" path="lastName" class="form-control" placeholder="Last name"></form:input>
                                    </div>
                                    <div class="col-9 offset-3 ml-2">
                                        <form:errors path="firstName" style="color: red;"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Email Address:</label>
                                    <div class="col-9">
                                        <form:input type="email" path="emailAddress" class="form-control" placeholder="Enter the email address" required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="emailAddress" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-3 control-label">Registering as:</label>
                                    <div class="col-9">
                                        <form:select class="form-control" path="role" required="true">
                                            <form:option value="ROLE_STUDENT">Student</form:option>
                                            <form:option value="ROLE_STAFF">Staff</form:option>
                                            <form:option value="ROLE_TEACHER">Teacher</form:option>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group mt-2">
                                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form:form>
                            <div class="form-group">
                                <div class="col-md-12 control">
                                    <div style="font-size:85%">
                                        Already Registered! <a href="/login">Login Here </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<hr class="featurette-divider">

<!-- FOOTER -->
<footer class="container">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>&copy; 2017-2020 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
</footer>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
    crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="js/jquery.slim.min.js"><\/script>')</script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script>
    $(".toggle-password").click(function () {
        $(this).toggleClass("fa-eye fa-eye-slash");
        var input = $('input[name = "password"]');
        if (input.attr("type") == "password") {
            input.attr("type", "text");
        } else {
            input.attr("type", "password");
        }
    });
</script>
</body>

</html>