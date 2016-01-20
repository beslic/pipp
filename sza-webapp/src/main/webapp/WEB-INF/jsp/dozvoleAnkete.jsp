<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Dozvole: ${anketa.nazivAnketa}</title>
<c:import url="/html/head.html" />
<script type='text/javascript'
	src='/sza-webapp/js/jquery.canvasjs.min.js'></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form method="post">
		<c:forEach var="anketar" items="${anketari}">
			<div class="col-md-10 ">
				<a style="font-size: 30px"
					href="../../../korisnici/${anketar.korisnickoIme}">${anketar.korisnickoIme}</a>
				<input style="display: inline" type="checkbox" name="dozvola">
			</div>
		</c:forEach>
		<div class="col-md-10 ">
			<button type="submit">Submit</button>
		</div>
	</form>

</body>
<style>
</style>
</html>