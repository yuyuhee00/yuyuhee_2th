<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:jsp_layout title="JSP Answer Form">

<jsp:attribute name="head_area">
</jsp:attribute>

<jsp:attribute name="body_area">

	<div class="container my-3">
		<h5 class="my-3 border-bottom pb-2"> 답 변 수 정 </h5>
		<form name="answerForm" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<jsp:include page="/WEB-INF/views/jsp/common/jsp_form_errors.jsp" />

			<div class="mb-3">
				<label for="content" class="form-label"> 내 용 </label>
				<textarea name="content" class="form-control" rows="10">${answerForm.content}</textarea>
			</div>
			<input type="submit" value=" 저 장 하 기" class="btn btn-primary my-2">
		</form>
	</div>

</jsp:attribute>

</t:jsp_layout>