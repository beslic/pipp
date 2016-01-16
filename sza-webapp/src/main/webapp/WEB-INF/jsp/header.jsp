<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
	    
		<div class="navbar-header">
	   		<a href="/sza-webapp/" class="navbar-brand"><strong>Sustav za anktetiranje</strong></a>
		</div>
	
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    
		    <ul class="nav navbar-nav navbar-left nav-pills">
		    	<li ${aktivAnkete}><a href="/sza-webapp/ankete/">Ankete</a></li>
		    	<c:if test="${sessionScope.korisnik != null}">
		    	    <li ${aktivMoj}><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/ankete/">Moje ankete</a></li>
		    	</c:if>
		    	<c:if test="${sessionScope.korisnik != null && sessionScope.korisnik.razinaPrava == 1}">
                    <li ${aktivNova}><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/ankete/nova">Nova anketa</a></li>
                    <li ${aktivAnk}><a href="/sza-webapp/anketari/">TODO: Anketari</a></li>
                </c:if>
		    </ul>
		      
			<ul class="nav navbar-nav navbar-right">
		    	<c:choose>
			        <c:when test="${sessionScope.korisnik != null}">
				        <li><p class="navbar-text">Prijavljen: <b>${sessionScope.korisnik.ime}</b></p></li>
				        <li ${aktivProfil}><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/">Moj profil</a></li>
				        <li>
				        	<form method="post" action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/">
				            	<button name="button" value="signout" class="btn btn-default navbar-btn" id="odjavaRight">Odjava</button>
				          	</form>
				        </li>
			        </c:when>
			        <c:otherwise>
			        	<c:choose>
			        	    <c:when test="${url == 2}">			
				        		<li><a href="/sza-webapp/prijava/">Prijava</a></li>
				            </c:when>
				            <c:when test="${url == 1}">			
   			        			<li><a href="/sza-webapp/registracija/">Registracija</a></li>
				            </c:when>
          			        <c:otherwise>
          			        	<li><a href="/sza-webapp/prijava/">Prijava</a></li>
   			        			<li><a href="/sza-webapp/registracija/">Registracija</a></li>
          			        </c:otherwise>
				        </c:choose>
			        </c:otherwise>
		        </c:choose>
			</ul>
    	</div>    
	</div>
</nav>
<script>
var link = window.location.href.split('/').splice(4, 3);
console.log("link je " + link);
console.log(link.indexOf('ankete') != -1 && link.indexOf('korisnici') != -1)
$(".nav a").on("click", function(){
	   $(".nav").find(".active").removeClass("active");
	   $(this).parent().addClass("active");
	});
</script>