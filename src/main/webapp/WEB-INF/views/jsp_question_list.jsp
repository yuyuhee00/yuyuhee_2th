<%--<%@ page import="com.mysite.sbb.utils.LocalDateTimeToString" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<%--<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="ko">--%>
<%--<head>--%>
<%--	<jsp:include page="/WEB-INF/views/jsp/common/meta.jsp" />--%>
<%--	<jsp:include page="/WEB-INF/views/jsp/common/link.jsp" />--%>
<%--	<jsp:include page="/WEB-INF/views/jsp/common/script.jsp" />--%>
<%--	<title>JSP Question List View</title>--%>
<%--</head>--%>
<%--<%--%>
<%--	LocalDateTimeToString convert = new LocalDateTimeToString();--%>
<%--	pageContext.setAttribute("convert", convert);--%>
<%--%>--%>
<%--<body>--%>
<%--	<jsp:include page="/WEB-INF/views/jsp/common/navbar.jsp" />--%>

<%--	<div class="container my-3">--%>
<%--		<table class="table">--%>
<%--			<thead class="table-dark">--%>
<%--				<tr class="text-center">--%>
<%--					<th> 번 호 </th>--%>
<%--					<th style="width:50%"> 제 목 </th>--%>
<%--					<th> 글 쓴 이 </th>--%>
<%--					<th> 작 성 일 시 </th>--%>
<%--				</tr>--%>
<%--			</thead>--%>

<%--			<tbody>--%>
<%--				<c:forEach var="question" items="${paging.content}" varStatus="loop">--%>
<%--					<tr>--%>
<%--						<td>No. ${paging.getTotalElements() - (paging.number * paging.size) - loop.index}</td>--%>
<%--						<td>--%>
<%--							<a href="/question/detail/${question.id}">${question.subject}</a>--%>
<%--							<span class="text-danger small ms-2">--%>
<%--								<c:if test="${question.answerList.size() > 0}">--%>
<%--									<span>${question.answerList.size()}</span>--%>
<%--								</c:if>--%>
<%--							</span>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<c:if test="${question.author != null}">--%>
<%--								<span>${question.author.username}</span>--%>
<%--							</c:if>--%>
<%--						</td>--%>
<%--						<td>${convert.formattedDate(question.createDate)}</td>--%>
<%--					</tr>--%>
<%--				</c:forEach>--%>
<%--			</tbody>--%>
<%--		</table>--%>

<%--		<!-- 페 이 징 처 리 시 작-->--%>
<%--		<div class="<c:if test="${!paging.isEmpty()}"> disabled</c:if>">--%>
<%--			<div>--%>
<%--				<ul class="pagination justify-content-center">--%>

<%--					<li class="page-item<c:if test="${!paging.hasPrevious()}"> disabled</c:if>">--%>
<%--						<a class="page-link" href="?page=${paging.number-1}">--%>
<%--							<span> 이 전 </span>--%>
<%--						</a>--%>
<%--					</li>--%>

<%--					<c:forEach var="page" begin="0" end="${paging.totalPages-1}">--%>
<%--						<li class="page-item<c:if test="${page == paging.number}"> active</c:if>">--%>
<%--							<c:if test="${page >= paging.number-5 and page <= paging.number+5}">--%>
<%--								<a class="page-link" href="?page=${page}">${page}</a>--%>
<%--							</c:if>--%>
<%--						</li>--%>
<%--					</c:forEach>--%>

<%--					<li class="page-item<c:if test="${!paging.hasNext()}"> disabled</c:if>">--%>
<%--						<a class="page-link" href="?page=${paging.number+1}">--%>
<%--							<span> 다 음 </span>--%>
<%--						</a>--%>
<%--					</li>--%>

<%--				</ul>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<!-- 페 이 징 처 리 끝-->--%>
<%--		<a href="/question/create" class="btn btn-primary"> 질 문 등 록 하 기 </a>--%>
<%--	</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mysite.sbb.utils.LocalDateTimeToString" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="scriptlit">
<%
	LocalDateTimeToString convert = new LocalDateTimeToString();
	pageContext.setAttribute("convert", convert);
%>
</c:set>

<t:jsp_layout title="JSP Question List">

<jsp:attribute name="head_area">
	${scriptlit}
</jsp:attribute>

<jsp:attribute name="body_area">
	<div class="container my-3">

		<div class="row my-3">
			<div class="col-6">
				<a href="/question/create" class="btn btn-primary">질문 등록하기 </a>
			</div>
			<div class="col-6">
				<form>
					<div class="input-group">
						<label for="search_kw"></label>
						<input type="text" id="search_kw" name="kw" class="form-control" value="${param.kw}">
						<button class="btn btn-outline-secondary" type="submit" id="btn_search">찾기</button>
					</div>
				</form>
			</div>
		</div>

		<table class="table">
			<thead class="table-dark">
				<tr class="text-center">
					<th> 번 호 </th>
					<th style="width:50%"> 제 목 </th>
					<th> 글 쓴 이 </th>
					<th> 작 성 일 시 </th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="question" items="${paging.content}" varStatus="loop">
					<tr>
						<td>No. ${paging.getTotalElements() - (paging.number * paging.size) - loop.index}</td>
						<td>
							<a href="/question/detail/${question.id}">${question.subject}</a>
							<span class="text-danger small ms-2">
								<c:if test="${question.answerList.size() > 0}">
									<span>${question.answerList.size()}</span>
								</c:if>
							</span>
						</td>
						<td>
							<c:if test="${question.author != null}">
								<span>${question.author.username}</span>
							</c:if>
						</td>
						<td>${convert.formattedDate(question.createDate)}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 페 이 징 처 리 시 작-->
		<c:set var="kwBase" value=" "/>
		<c:choose>
			<c:when test="${param.kw != null}">
				<c:set var="kwBase" value="?kw=${param.kw}"/>
			</c:when>
			<c:otherwise>
				<c:set var="kwBase" value="?kw="/>
			</c:otherwise>
		</c:choose>

		<c:if test="${!paging.isEmpty()}">
			<div>
				<ul class="pagination justify-content-center">
					<li class="page-item<c:if test="${!paging.hasPrevious()}"> disabled</c:if>">
						<a class="page-link" href="${kwBase}&page=${paging.number-1}">
							<span> 이 전 </span>
						</a>
					</li>
					<c:forEach var="page" begin="0" end="${paging.totalPages-1}">
						<li class="page-item<c:if test="${page == paging.number}"> active</c:if>">
							<c:if test="${page >= paging.number-5 and page <= paging.number+5}">
								<a class="page-link" href="${kwBase}&page=${page}">${page+1}</a>
							</c:if>
						</li>
					</c:forEach>
					<li class="page-item<c:if test="${!paging.hasNext()}"> disabled</c:if>">
						<a class="page-link" href="${kwBase}&page=${paging.number+1}">
							<span> 다 음 </span>
						</a>
					</li>
				</ul>
			</div>
		</c:if>
		<!-- 페 이 징 처 리 끝-->

		<form action="/question/list" method="get" id="searchForm">
			<input type="hidden" id="kw" name="kw" value="${kw}">
			<input type="hidden" id="page" name="page" value="${paging.number}">
		</form>
	</div>
</jsp:attribute>

</t:jsp_layout>