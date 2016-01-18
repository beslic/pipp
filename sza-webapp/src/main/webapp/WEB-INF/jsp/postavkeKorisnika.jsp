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

	<div class="container"  id="korisnikDetails">
		<h2>Izmjena podataka o korisniku</h2>
	
		<br>
	

	<div class="tabbable">
		<ul class="nav nav-tabs" role="tablist" id="tab">
			<li role="presentation" class="active"><a href="#tab1"
				aria-controls="tab1" role="tab" data-toggle="tab">Osobni podatci</a></li>
			<li role="presentation"><a href="#tab2" aria-controls="tab2"
				role="tab" data-toggle="tab">Izmjena lozinke</a></li>

		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="tab1">

				<form class="form-horizontal" method="post"
					action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/postavke/">

					<hr>
					<!-- First Name input-->
					<div class="form-group">
						<label id="usernamelabel" class="col-md-4 control-label"
							for="firstnameinput">Ime</label>
						<div
							class="col-md-4 <c:if test="${greska.ime != null}">has-error has-feedback</c:if>">
							<input id="firstnameinput" name="ime" type="text"
								class="form-control input-md"
								value="${sessionScope.korisnik.ime}"
								aria-describedby="errorstatus" autofocus>
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
					</div>
				</form>



			</div>
			<div role="tabpanel" class="tab-pane" id="tab2">


				<form class="form-horizontal" method="post"
					action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/postavke/">

					<hr>
					<!-- Old Password input-->
					<div class="form-group">
						<label id="staraLozinkalabel" class="col-md-4 control-label"
							for="confirmpasswordinput">Stara lozinka</label>
						<div
							class="col-md-6 <c:if test="${greska.staralozinka != null}">has-error has-feedback</c:if>">
							<input id="staralozinka" name="staralozinka" type="password"
								class="form-control input-md"
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
							class="col-md-6 <c:if test="${greska.novalozinka != null}">has-error has-feedback</c:if>">
							<input id="passwordinput" name="novalozinka" type="password"
								class="form-control input-md" placeholder="Unesite novu lozinku"
								aria-describedby="errorstatus">
							<c:if test="${greska.novalozinka != null}">
								<span class="glyphicon glyphicon-remove form-control-feedback"
									aria-hidden="true"></span>
								<label class="control-label" for="passwordinput">${greska.novalozinka}</label>
								<span id="errorstatus" class="sr-only">(error)</span>
								<script type="text/javascript">
									$(document).ready(function() {
										activaTab('tab2');
									});
									function activaTab(tab) {
										$('.nav-tabs a[href="#' + tab + '"]')
												.tab('show');
									};
								</script>

							</c:if>
						</div>
					</div>
					<!-- Confirm Password input-->
					<div class="form-group">
						<label id="confirmpasswordlabel" class="col-md-4 control-label"
							for="confirmpasswordinput">Potvrdite lozinku</label>
						<div
							class="col-md-6 <c:if test="${greska.novalozinkapotvrda != null}">has-error has-feedback</c:if>">
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
							value="postavkelozinka" class="btn btn-success">Spremi
							lozinku</button>
					</div>
				</form>



			</div>

		</div>


		<script>
			$('#tab a').click(function(e) {
				e.preventDefault();
				$(this).tab('show');
			});

			// store the currently selected tab in the hash value
			$("ul.nav-tabs > li > a").on("shown.bs.tab", function(e) {
				var id = $(e.target).attr("href").substr(1);
				window.location.hash = id;
			});

			// on load of the page: switch to the currently selected tab
			var hash = window.location.hash;
			$('#tab a[href="' + hash + '"]').tab('show');
		</script>




</div>



	</div>






</body>
</html>