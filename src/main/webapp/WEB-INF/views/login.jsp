<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="login-form section text-center py-5">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 offset-sm-3">
                <div class="mt-5">
                    <div class="card card-info">
                        <div class="card-header" style="color: #fff; background-color:#333;">
                            LOGIN
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" action="login" method="post">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="input-group justify-content-center">
                                            <span style="color: green;">${message}</span>
                                        </div>
                                        <div class="input-group mt-2">
                                            <span class="input-group-addon" style="color: #fff; background-color: #333;">
                                                <i class="fa fa-user" style="margin: 10px 15px;"></i>
                                            </span>
                                            <input id="username" type="text" class="form-control" name="username" value=""
                                                placeholder="Username" autofocus="true" required>
                                        </div>

                                        <div class="input-group mt-4">
                                            <span class="input-group-addon" style="color: #fff; background-color:#333;">
                                                <i class="fa fa-lock" style="margin: 10px 15px;"></i>
                                            </span>
                                            <input id="password" type="password" class="form-control" name="password"
                                                placeholder="Password" required>
                                        </div>
                                        <div class="input-group justify-content-center mt-3">
                                            <span style="color: red;">${error}</span>
                                        </div>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                        <div class="input-group mt-2">
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                                        </div>
                                    </div>
                                </div>
                
                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="padding-top:15px; font-size:85%">
                                            Don't have an account! <a href="/register"> Register Here </a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>