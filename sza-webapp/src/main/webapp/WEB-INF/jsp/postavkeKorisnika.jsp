<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Postavke - ${sessionScope.korisnik.korisnickoIme}</title>
<c:import url="/html/head.html" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<form method="post" action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/postavke/">
	<div class="container" id="korisnikDetails">
		<h2>Izmjena podataka o korisniku</h2>
		<hr>
		<br>
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
					<td><input id="firstnameinput" name="ime" type="text"
						class="form-control input-md" 
						value="${sessionScope.korisnik.ime}"
						aria-describedby="errorstatus" autofocus="true"></td>
				</tr>
				<tr>
					<td>Prezime</td>
					<td><input id="lastnameinput" name="prezime" type="text"
						class="form-control input-md" 
						value="${sessionScope.korisnik.prezime}"
						aria-describedby="errorstatus" autofocus="true"></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input id="emailinput" name="email" type="text"
						class="form-control input-md"
						value="${sessionScope.korisnik.email}"
						aria-describedby="errorstatus" autofocus="true"></td>

				</tr>
				<tr>
					<td>Uloga</td>
					<td><c:choose>
							<c:when test="${sessionScope.korisnik.razinaPrava == 0}">
			           	Administrator sustava 
		        </c:when>
							<c:when test="${sessionScope.korisnik.razinaPrava == 1}">
			           	Narucitelj ankete 
		        </c:when>
							<c:otherwise>
		            	Anketar
		        </c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>Aktivnost</td>
					<td><c:choose>
							<c:when test="${sessionScope.korisnik.aktivan}">
			           	Aktivan korisnik  
		        </c:when>
							<c:otherwise>
		            	Neaktivan korisnik
		        </c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
		<div class="col-md-4">
			
				<button id="postavke" name="buttonPostavke" value="postavke" class="btn btn-success">Spremi</button>
			</form>
		</div>
	</div>



</body>
</html>