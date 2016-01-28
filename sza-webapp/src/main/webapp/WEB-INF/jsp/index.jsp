<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Sustav za anketiranje</title>
		<c:import url="/html/head.html" />
	</head>
	<body>
		<jsp:include page="header.jsp" />

		<div class="container" id="odvojiJumbotronOdHeadera">
			<div class="jumbotron">
				<h1><span class="glyphicon glyphicon-list-alt"></span> Sustav za anketiranje</h1>
				<p>Sustav za provođenje anketiranja putem web aplikacije i mobilne aplikacije</p>
				<p><b>Web aplikacija </b><span class="glyphicon glyphicon-chevron-right"></span> kreiranje anketa i ispunjavanje anketa online</p>
				<p><b>Mobilna aplikacija </b><span class="glyphicon glyphicon-chevron-right"></span> ispunjavanje anketa</p>
				<br>
				<c:if test="${sessionScope.korisnik != null}">
				<p><a class="btn btn-primary btn-lg" href="#" role="button">Preuzmite mobilnu aplikaciju</a></p>
				</c:if>
			</div>
		</div>

		<div class="container">
			<div class="col-md-12">
				<hr>
			</div>
		</div>

		<div class="container">

		<div class="panel panel-primary">
		  	<div class="panel-heading"><b>Anketiranje</b></div>
		  		<div class="panel-body">
					Anketiranje je posebna metoda prikupljanja podataka uz čiju
					pomoć dolazimo do podataka o stavovima i mišljenjima anketiranih
					ispitanika. Anketiranje se provodi postavljanjem točno određenih
					pitanja određenoj grupi ljudi na koje oni odgovaraju jednim ili
					 više odgovora. Anketiranje koriste osobe ili tvrtke koje žele
					  provjeriti zadovoljstvo stranaka proizvodima, uslugama, otkriti
					   želje i potrebe klijenata, javno mišljenje stranaka o određenim
						temama, itd. Razvojem novih tehnologija, prvenstveno telefona
						i interneta, dolazi do olakšanja provođenja anketiranja.
		  		</div>
			</div>
		</div>

<div id="footer">
	<h3><small id="onama"><a href="/sza-webapp/tim">O nama</a></small><span id="icon" class="glyphicon glyphicon-option-horizontal"></span></h3>
	<p>2016 <span class="glyphicon glyphicon-copyright-mark"></span> Projekt iz programske potpore</p>

</div>
	<script>
		$("#onama").hide();

		$("#footer").mouseover(function () {
			$("#icon").fadeOut("fast", function () {
				$("#onama").show();
			});
		}).mouseleave(function () {
			$("#onama").hide();
			$("#icon").fadeIn("fast");
		});
	</script>


</body>
</html>
