<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
		<div class="row">

			<h3>Čekaju aktivaciju:</h3>
			<p><button class="btn btn-primary btn-xs" onclick="checkItems()">Select All</button></p>
			

			<form method="post">

				<div class="col-md-3">

					<c:forEach var="userNeakt" items="${korisniciNeakt}">
						<h4 style="display: inline;">
							<a href="#">${userNeakt.korisnickoIme}</a>
						</h4>
						
						<span class="pull-right"> <input class="pull-right"
							name="nepotvrdjen-${userNeakt.korisnickoIme}" class="input-md"
							type="checkbox">&nbsp;Aktiviraj
						</span>
					</c:forEach>
					<br>
					<h3>Aktivni:</h3>
					<c:forEach var="userAkt" items="${korisniciAkt}">

                                          <h4 style="display: inline;">
                                                <a href="#">${userAkt.korisnickoIme}</a>
                                          </h4>
                                          <span class="pull-right">
                                          <input name="potvrdjen-${userAkt.korisnickoIme}" class="pull-right"
                                                  type="checkbox">&nbsp;Deaktiviraj</span>
                                          <br>
                                        </c:forEach>

				</div>

				<div class="col-md-3">
					<button id="activate" type="submit" name="activate"
						value="activate" class="btn btn-primary">Spremi promjene</button>
				</div>
				<div class="col-md-6">
				  <h3>Žele povećanje prava:</h3>
				  <c:forEach var="userPov" items="${cekajuPrava}">

                                          <h4 style="display: inline;">
                                                <a href="#">${userPov.korisnickoIme}</a>
                                          </h4>
                                          <span class="pull-right"></span>
                                          <input name="prava-${userPov.korisnickoIme}" class="input-md"
                                                  type="checkbox">&nbsp;Povećaj</span>
                                  </c:forEach>
				</div>
		</div>


		</form>

	</div>
	
<script type="text/javascript">
        function checkItems() {

        var test = document.querySelectorAll('input[name][type="checkbox"]:not([name^="potvrdjen-"])');

	for (var i = 0; i < test.length; i++) {
	    test[i].checked = true
	}
	  
	}
</script>
	
</body>
</html>