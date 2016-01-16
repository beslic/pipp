<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <c:import url="/html/head.html" />
    <title>Podatci o korisniku</title>
</head>

<body>
    <jsp:include page="header.jsp" />
     
<div class="container" id="korisnikDetails">
 <h2>Podatci o korisniku</h2>
 <hr>
 <br>    
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Korisničko ime</th>
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
			           	Naručitelj ankete 
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
		            	Neaktivan korisnik
		        </c:otherwise>
     		</c:choose>
        
        </td>
      </tr>
    </tbody>
  </table>
	<div class="col-md-4">
	<button id="postavke" name="postavke" onclick="location.href='/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/postavke/'" class="btn btn-success">Izmjeni podatke</button>
	</div>
</div>
			
     
    
</body>
</html>