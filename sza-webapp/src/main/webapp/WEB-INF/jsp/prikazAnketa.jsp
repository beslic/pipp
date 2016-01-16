<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Ankete</title>
    <c:import url="/html/head.html" />
    <link rel="stylesheet" href="/sza-webapp/css/datepicker.css">
    <link rel="stylesheet" href="/sza-webapp/css/datepicker3.css">
    <script type='text/javascript' src='/sza-webapp/js/bootstrap-datepicker.js'></script>
</head>

<body>

    <div class="container">
	    <c:if test="${ankete == null}">
	        <h3>Nemate još nijednu anketu</h3>
	    </c:if>
	    <c:forEach var="anketa" items="${ankete}">
	        <c:set var="uri" value="${anketa.nazivAnketa}"/>
	        <h3><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/ankete/${fn:replace(uri, ' ', '-')}/">${anketa.nazivAnketa}</a></h3>
	    </c:forEach>
    </div>
    
    
</body>
</html>