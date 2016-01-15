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

	<div class="container" id="korisnikDetails">
		<h2>Izmjena podataka o korisniku</h2>
		<hr>
		<br>


		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab">Osobni podatci</a></li>
			<li role="presentation"><a href="#profile"
				aria-controls="profile" role="tab" data-toggle="tab">Izmjena
					lozinke</a></li>

		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">

				<form class="form-horizontal" method="post"
					action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/postavke/">


					<!-- First Name input-->
					<div class="form-group">
						<label id="usernamelabel" class="col-md-4 control-label"
							for="firstnameinput">Ime</label>
						<div
							class="col-md-4 <c:if test="${greska.ime != null}">has-error has-feedback</c:if>">
							<input id="firstnameinput" name="ime" type="text"
								class="form-control input-md"
								value="${sessionScope.korisnik.ime}"
								aria-describedby="errorstatus" autofocus="true">
							<c:if test="${greska.ime != null}">
								<span class="glyphicon glyphicon-remove form-control-feedback"
									aria-hidden="true"></span>
								<label class="control-label" for="firstnameinput">${greska.ime}</label>
								<span id="errorstatus" class="sr-only">(error)</span>
							</c:if>
						</div>
					</div>
					<!-- Last Name input-->
					<div class="form-group">
						<label id="lastnamelabel" class="col-md-4 control-label"
							for="lastnameinput">Prezime</label>
						<div
							class="col-md-4 <c:if test="${greska.prezime != null}">has-error has-feedback</c:if>">
							<input id="lastnameinput" name="prezime" type="text"
								class="form-control input-md"
								value="${sessionScope.korisnik.prezime}"
								aria-describedby="errorstatus">
							<c:if test="${greska.prezime != null}">
								<span class="glyphicon glyphicon-remove form-control-feedback"
									aria-hidden="true"></span>
								<label class="control-label" for="lastnameinput">${greska.prezime}</label>
								<span id="errorstatus" class="sr-only">(error)</span>
							</c:if>
						</div>
					</div>
					<!-- Email Address input-->
					<div class="form-group">
						<label id="emaillabel" class="col-md-4 control-label"
							for="emailinput">Email adresa</label>
						<div
							class="col-md-4 <c:if test="${greska.email != null}">has-error has-feedback</c:if>">
							<input id="emailinput" name="email" type="text"
								class="form-control input-md"
								value="${sessionScope.korisnik.email}"
								aria-describedby="errorstatus">
							<c:if test="${greska.email != null}">
								<span class="glyphicon glyphicon-remove form-control-feedback"
									aria-hidden="true"></span>
								<label class="control-label" for="emailinput">${greska.email}</label>
								<span id="errorstatus" class="sr-only">(error)</span>
							</c:if>
						</div>
					</div>


					<div class="col-md-4">

						<button id="postavke" name="buttonPostavke" value="postavke"
							class="btn btn-success">Spremi</button>
				</form>
			</div>



		</div>
		<div role="tabpanel" class="tab-pane" id="profile">


			<form class="form-horizontal" method="post"
				action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/postavke/">

				<!-- Old Password input-->
				<div class="form-group">
					<label id="staraLozinkalabel" class="col-md-4 control-label"
						for="confirmpasswordinput">Stara lozinka</label>
					<div
						class="col-md-4 <c:if test="${greska.staraLozinka != null}">has-error has-feedback</c:if>">
						<input id="staralozinka" name="staralozinka"
							type="password" class="form-control input-md"
							placeholder="Unesite staru lozinku">
						<c:if test="${greska.staralozinka != null}">
							<span class="glyphicon glyphicon-remove form-control-feedback"
								aria-hidden="true"></span>
							<label class="control-label" for="confirmpasswordinput">${greska.staralozinka}</label>
							<span id="errorstatus" class="sr-only">(error)</span>
						</c:if>
					</div>
				</div>



				<!-- Password input-->
				<div class="form-group">
					<label id="passwordlabel" class="col-md-4 control-label"
						for="passwordinput">Nova lozinka</label>
					<div
						class="col-md-4 <c:if test="${greska.novalozinka != null}">has-error has-feedback</c:if>">
						<input id="passwordinput" name="novalozinka" type="password"
							class="form-control input-md" placeholder="Unesite novu lozinku"
							aria-describedby="errorstatus">
						<c:if
							test="${greska.novalozinka != null}">
							<span class="glyphicon glyphicon-remove form-control-feedback"
								aria-hidden="true"></span>
							<label class="control-label" for="passwordinput">${greska.novalozinka}${greska.novalozinkapotvrda}</label>
							<span id="errorstatus" class="sr-only">(error)</span>
						</c:if>
					</div>
				</div>
				<!-- Confirm Password input-->
				<div class="form-group">
					<label id="confirmpasswordlabel" class="col-md-4 control-label"
						for="confirmpasswordinput">Potvrdite lozinku</label>
					<div
						class="col-md-4 <c:if test="${greska.novalozinkapotvrda != null}">has-error has-feedback</c:if>">
						<input id="confirmpasswordinput" name="novalozinkapotvrda"
							type="password" class="form-control input-md"
							placeholder="Potvrdite novu lozinku">
						<c:if test="${greska.novalozinkapotvrda != null}">
							<span class="glyphicon glyphicon-remove form-control-feedback"
								aria-hidden="true"></span>
							<label class="control-label" for="confirmpasswordinput">${greska.novalozinkapotvrda}</label>
							<span id="errorstatus" class="sr-only">(error)</span>
						</c:if>
					</div>
				</div>


				<div class="col-md-4">

					<button id="postavkelozinka" name="buttonPostavke"
						value="postavkelozinka" class="btn btn-success">Spremi lozinku</button>
			</form>
		</div>



	</div>

	</div>

	</div>





	</div>



</body>
</html>