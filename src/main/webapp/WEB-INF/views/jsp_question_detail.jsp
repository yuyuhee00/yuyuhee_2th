<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.yuyuhee_2th.utils.LocalDateTimeToString" %>
<%@ page import="com.example.yuyuhee_2th.utils.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<c:set var="scriptlit">
<%
	LocalDateTimeToString convert = new LocalDateTimeToString();
	pageContext.setAttribute("convert", convert);

	CommonUtil commonUtil = new CommonUtil();
	pageContext.setAttribute("commonUtil", commonUtil);
%>
</c:set>

<t:jsp_layout title="JSP Question Detail">

<jsp:attribute name="head_area">
	${scriptlit}
</jsp:attribute>

<jsp:attribute name="body_area">
	<div class="container my-3">

		<!-- 질 문-->
		<div class="d-flex justify-content-start">
			<h2 class="border-bottom py-2">${question.subject}</h2>
		</div>

		<div class="card my-3">
			<div class="card-body">
				<div class="card-text">${commonUtil.markdown(question.content)}</div>
				<div class="d-flex justify-content-end">
					<div class="badge bg-light text-dark p-2 text-start">
						<div class="mb-2">
							<c:if test="${question.author != null}">
								<span>${question.author.username}</span>
							</c:if>
						</div>
						<div>${convert.formattedDate(question.createDate)}</div>
					</div>
				</div>
				<div class="my-3">
					<sec:authorize access="isAuthenticated()">
						<a class="recommend_question btn btn-sm btn-outline-secondary">
							추 천
							<span class="recommend_question_count badge rounded-pill bg-success">${question.voter.size()}</span>
						</a>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<c:if test="${question.author != null}">
							<sec:authentication property="principal.username" var="currentUserName"/>
							<c:if test="${currentUserName == question.author.username}">
								<a class="btn btn-sm btn-outline-secondary" href="/question/modify/${question.id}">수 정</a>
							</c:if>
						</c:if>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<c:if test="${question.author != null}">
							<sec:authentication property="principal.username" var="currentUserName"/>
							<c:if test="${currentUserName == question.author.username}">
								<a class="delete btn btn-sm btn-outline-secondary" href="/question/delete/${question.id}">삭 제</a>
							</c:if>
						</c:if>
					</sec:authorize>
				</div>
			</div>
		</div>

		<!-- 답 변 의 갯 수 표 시-->
		<h5 class="border-bottom my-3 py-2">${question.answerList.size()} 개 의 답 변 이 있 습 니 다</h5>

		<!-- 답 변 반 복 시 작-->
		<c:forEach var="answer" items="${question.answerList}">
			<a id="answer_${answer.id}"></a>
			<div class="card my-3">
				<div class="card-body">
					<div class="card-text">${commonUtil.markdown(answer.content)}</div>
					<div class="d-flex justify-content-end">
						<div class="badge bg-light text-dark p-2 text-start">
							<div class="mb-2">
								<c:if test="${question.author != null}">
									<span>${answer.author.username}</span>
								</c:if>
							</div>
							<div>${convert.formattedDate(answer.createDate)}</div>
						</div>
					</div>
					<div class="my-3">
						<sec:authorize access="isAuthenticated()">
							<a class="recommend_answer btn btn-sm btn-outline-secondary">
								<input type="hidden" name="answer_id" value="${answer.id}">
								추 천
								<span class="recommend_answer_count badge rounded-pill bg-success">${answer.voter.size()}</span>
							</a>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<c:if test="${answer.author != null}">
								<sec:authentication property="principal.username" var="currentUserName"/>
								<c:if test="${currentUserName == answer.author.username}">
									<a class="btn btn-sm btn-outline-secondary" href="/answer/modify/${answer.id}">수 정</a>
								</c:if>
							</c:if>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<c:if test="${answer.author != null}">
								<sec:authentication property="principal.username" var="currentUserName"/>
								<c:if test="${currentUserName == answer.author.username}">
									<a class="delete btn btn-sm btn-outline-secondary" href="/answer/delete/${answer.id}">삭 제</a>
								</c:if>
							</c:if>
						</sec:authorize>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- 답 변 반 복 끝-->

		<!-- 답 변 작 성-->
		<form name="answerForm" action="/answer/create/${question.id}" method="post" class="my-3">
			<jsp:include page="/WEB-INF/views/common/jsp_form_errors.jsp" />
			<sec:authorize access="isAnonymous()">
				<textarea class="form-control" name="content" rows="10" placeholder="로그인을 해주세요"></textarea>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<textarea class="form-control" name="content" rows="10"></textarea>
			</sec:authorize>
			<input type="submit" value="저장하기" class="btn btn-primary my-2">
		</form>
	</div>
</jsp:attribute>

<jsp:attribute name="script_area">
	<script type="text/javascript">

		$(document).ready(function () {

			$(".recommend_question").on("click", function() {
				const question_id = [[${question.id}]];
				console.log("Question id -----> " + question_id);

				if( confirm('정말 추천하시겠습니까?') ) {
					$.ajax({
						url: "/question/vote/" + question_id, // 요청 보낼 서버 URL
						type: "GET", // 요청 메서드("GET", "PUT", "POST", "DELETE") 등
						success: function(response) {
							// 요청이 성공했을 때 실행되는 함수
							$(".recommend_question_count").text(response);
							console.log("응답 : " + response);
						},
						error: function(xhr, status, error) {
							// 요청이 실패했을 때 실행되는 함수
							console.log("에러 : " + error);
						}
					});
				}
			});

			$(".recommend_answer").on("click", function() {
				const _this = $(this);
				const answer_id = _this.find('input[name=answer_id]').val();
				console.log("Answer id -----> " + answer_id);

				if( confirm('정말 추천하시겠습니까?') ) {
					$.ajax({
						url: "/answer/vote/" + answer_id, // 요청 보낼 서버 URL
						type: "GET", // 요청 메서드("GET", "PUT", "POST", "DELETE") 등
						success: function(response) {
							// 요청이 성공했을 때 실행되는 함수
							$(".recommend_answer_count").text(response);
							console.log("응답 : " + response);
						},
						error: function(xhr, status, error) {
							// 요청이 실패했을 때 실행되는 함수
							console.log("에러 : " + error);
						}

					});
				}
			});
		});
	</script>
</jsp:attribute>

</t:jsp_layout>