<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Ankete</title>
    <c:import url="/html/head.html" />
</head>

<body>
    <jsp:include page="header.jsp" />
    <div class="container">
      <div class="col-md-10">
        <h2>${naslov}</h2>
        <hr>
      </div>
      <c:if test="${ankete == null}">
        <h3>${nema}</h3>
      </c:if>
	  <div class="col-md-10">
	    <c:forEach var="anketa" items="${ankete}">
	      <c:set var="uri" value="${anketa.idAnketa}-${anketa.nazivAnketa}"/>
	      <div class="col-md-6 col-md-offset-1">
	        <h3><a href="/sza-webapp/${url}ankete/${fn:replace(uri, ' ', '-')}/">${anketa.nazivAnketa}</a></h3>
	      </div>
	    </c:forEach>
	  </div>
	</div>
</body>
</html>