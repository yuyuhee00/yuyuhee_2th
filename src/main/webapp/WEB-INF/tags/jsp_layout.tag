<!DOCTYPE html>
<%@tag description="SBB Template" pageEncoding="UTF-8"%>

<%--외부에서 문자열을 받을 수 있다--%>
<%@attribute name="title"%>

<%--외부에서 HTML 요소를 받을 수 있다--%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>
<%@attribute name="script_area" fragment="true" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" type="text/css" href="/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/style.css"  />

        <%--EL 표현식을 사용하여 외부에서 전달된 문자열을 사용한다--%>
        <title>SBB - ${title}</title>

        <%--invoke 사용하여 외부에서 전달된 HTML요소를 사용한다--%>
        <jsp:invoke fragment="head_area"/>

    </head>
    <body>

        <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

        <%--invoke 사용하여 외부에서 전달된 HTML요소를 사용한다--%>
        <jsp:invoke fragment="body_area"/>

        <jsp:invoke fragment="script_area"/>

    </body>

</html>

