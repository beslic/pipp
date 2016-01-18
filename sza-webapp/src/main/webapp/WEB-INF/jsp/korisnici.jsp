<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Korisnici</title>
    <c:import url="/html/head.html" />
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="container">
      <h2>Korisnici</h2>
      <br>
      
      <h3>Čekaju aktivaciju:</h3>
      <c:forEach var="userNeakt" items="${korisniciNeakt}">
                <h4>${userNeakt.korisnickoIme}</h4>
      </c:forEach>
      
      <h3>Aktivni:</h3>
      <c:forEach var="userAkt" items="${korisniciAkt}">
                <h4>${userAkt.korisnickoIme}</h4>
      </c:forEach>
      
    </div>
</body>
</html>