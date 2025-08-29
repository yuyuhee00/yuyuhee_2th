<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:jsp_layout title="JSP Login Form">

<jsp:attribute name="head_area">
</jsp:attribute>

<jsp:attribute name="body_area">
    <div class="container my-3">
        <form action="/user/login" method="post">
            <div>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">사 용 자 ID 또 는 비 밀 번 호 를 확 인 해 주 세 요.</div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label"> 사 용 자 ID</label>
                <input type="text" name="username" id="username" class="form-control">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"> 비 밀 번 호 </label>
                <input type="password" name="password" id="password" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary"> 로 그 인 </button>
        </form>
    </div>
</jsp:attribute>

</t:jsp_layout>