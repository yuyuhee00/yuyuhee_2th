<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<!DOCTYPE html>
<html>
<%--    <div th:fragment="formErrorsFragment" class="alert alert-danger" role="alert" th:if="${#fields.hasAnyErrors()}">--%>
<%--        <div th:each="err : ${#fields.allErrors()}" th:text="${err}" />--%>
<%--    </div>--%>

    <div class="'alert alert-danger'<c:if test="${!fields.hasAnyErrors()}"> disabled</c:if>" role="alert">
        <c:forEach var="errors" items="${fields.allErrors()}">
            <span th:text="${error}"></span>
        </c:forEach>
    </div>
</html>
