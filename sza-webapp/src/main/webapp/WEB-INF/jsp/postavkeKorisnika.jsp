<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Postavke - ${sessionScope.korisnik.korisnickoIme}</title>
    <c:import url="/html/head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Ovdje idu postavke korisnika ${sessionScope.korisnik.korisnickoIme} karlo</h2>
<div class="container">
<div class="col-md-6">
<table class="table table-striped">
    <thead>
      <tr>
        <th>Korisnicko ime</th>
        <th><b>${sessionScope.korisnik.korisnickoIme}</b></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Ime</td>
        <td>${sessionScope.korisnik.ime}</td>
      </tr>
      <tr>
        <td>Prezime</td>
        <td>${sessionScope.korisnik.prezime}</td>
      </tr>
      <tr>
        <td>Email</td>
        <td>${sessionScope.korisnik.email}</td>
        
      </tr>
      <tr>
        <td>Uloga</td>
        <td>
         	<c:choose>
         		<c:when test="${sessionScope.korisnik.razinaPrava == 0}">
			           	Administrator sustava 
		        </c:when>
		        <c:when test="${sessionScope.korisnik.razinaPrava == 1}">
			           	Narucitelj ankete 
		        </c:when>
		        <c:otherwise>
		            	Anketar
		        </c:otherwise>
    		</c:choose>
        </td>
      </tr>
      <tr>
        <td>Aktivnost</td>
        <td>
        	<c:choose>
		        <c:when test="${sessionScope.korisnik.aktivan}">
			           	Aktivan korisnik  
		        </c:when>
		        <c:otherwise>
		            	Neaktivan korisnikx
		        </c:otherwise>
     		</c:choose>
        
        </td>
      </tr>
    </tbody>
  </table>
	
</div>
</div>
</body>
</html>