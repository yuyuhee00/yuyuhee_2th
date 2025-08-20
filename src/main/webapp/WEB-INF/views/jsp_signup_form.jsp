<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:jsp_layout title="JSP User Signup Form">

<jsp:attribute name="head_area">
</jsp:attribute>

<jsp:attribute name="body_area">

    <div class="container my-3">
        <div class="my-3 border-bottom">
            <div>
                <h4> 회 원 가 입 </h4>
            </div>
        </div>

        <form name="userCreateForm" action="/user/signup" method="post">
            <jsp:include page="/WEB-INF/views/jsp/common/jsp_form_errors.jsp" />
            <div class="mb-3">
                <label for="username" class="form-label"> 사 용 자 ID</label>
                <input type="text" name="username" class="form-control">
            </div>
            <div class="mb-3">
                <label for="password1" class="form-label"> 비 밀 번 호 </label>
                <input type="password" name="password1" class="form-control">
            </div>
            <div class="mb-3">
                <label for="password2" class="form-label"> 비 밀 번 호 확 인 </label>
                <input type="password" name="password2" class="form-control">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label"> 이 메 일 </label>
                <input type="email" name="email" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary"> 회 원 가 입 </button>
        </form>
    </div>

</jsp:attribute>

</t:jsp_layout>