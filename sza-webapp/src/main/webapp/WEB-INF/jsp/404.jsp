<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <c:import url="/html/head.html" />
    <title>404</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <h2>HTTP status 404</h2>
    <h3>Stranica nije pronađena!</h3>
</body>
</html>