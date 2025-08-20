<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:jsp_layout title="JSP Answer List">

<jsp:attribute name="head_area">
</jsp:attribute>

<jsp:attribute name="body_area">

	<div class="container my-3">

		<h3>${name}</h3>
		<div>JSP List Test</div>
		<c:forEach var="item" items="${list}" varStatus="idx">
			${idx.index}, ${item} <br />
		</c:forEach>
	</div>

</jsp:attribute>

</t:jsp_layout>