<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE>
<html>
    <nav th:fragment="navbarFragment" class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">SBB</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <sec:authorize access="isAnonymous()"><a class="nav-link" href="/user/login">로 그 인</a></sec:authorize>
                        <sec:authorize access="isAuthenticated()"><a class="nav-link" href="/user/logout">로 그 아 웃</a></sec:authorize>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/signup"> 회 원 가 입 </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</html>
