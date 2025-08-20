%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:jsp_layout title="JSP Question Form">

<jsp:attribute name="head_area">
</jsp:attribute>

<jsp:attribute name="body_area">
    <div class="container">
        <h5 class="my-3 border-bottom pb-2"> 질 문 등 록 </h5>
        <form name="questionForm" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <jsp:include page="/WEB-INF/views/common/jsp_form_errors.jsp" />
            <div class="mb-3">
                <label for="subject" class="form-label"> 제 목 </label>
                <input type="text" name="subject" value="${questionForm.subject}" class="form-control" autocomplete="off" required maxlength="200">
            </div>
            <div class="mb-3">
                <label for="content" class="form-label"> 내 용 </label>
                <textarea name="content" class="form-control" rows="10" required maxlength="3000">${questionForm.content}</textarea>
            </div>
            <input type="submit" value=" 저 장 하 기" class="btn btn-primary my-2">
        </form>
    </div>
</jsp:attribute>

</t:jsp_layout>