<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="headershadow"class="navbar navbar-inverse navbar-fixed-top">
	<div class="col-md-10 col-md-offset-1 container-fluid">

		<div class="navbar-header">
	   		<a id="logoUrlLook" href="/sza-webapp/" class="navbar-brand"><span id="homeLogo" class="glyphicon glyphicon-home" aria-hidden="true"></span><strong> Sustav za anketiranje</strong></a>
		</div>
	
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    
		    <ul class="nav navbar-nav navbar-left">
		    	<li ${aktivAnkete}><a href="/sza-webapp/ankete/">Ankete</a></li>
		    	<c:if test="${sessionScope.korisnik != null}">
		    	    <li ${aktivMoje}><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/ankete/">Moje ankete</a></li>
		    	</c:if>
		    	<c:if test="${sessionScope.korisnik != null && sessionScope.korisnik.razinaPrava <= 1}">
                    <li ${aktivNova}><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/ankete/nova/">Nova anketa</a></li>
                    <li ${aktivAnk}><a href="/sza-webapp/anketari/">Anketari</a></li>
                </c:if>
                       <c:if test="${sessionScope.korisnik != null && sessionScope.korisnik.razinaPrava == 0}">
                           <li><a href="/sza-webapp/korisnici/">Korisnici&nbsp;<c:if test="${sessionScope.cekajuPotvrdu > 0}"><span class="badge">${sessionScope.cekajuPotvrdu}</span></c:if></a></li>
                       </c:if>
		    </ul>
		      
			<ul class="nav navbar-nav navbar-right">
		    	<c:choose>
			        <c:when test="${sessionScope.korisnik != null}">
				        <li><p class="navbar-text">Prijavljen: <b>${sessionScope.korisnik.ime}</b></p></li>
				        <li ${aktivProfil}><a href="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/">Moj profil</a></li>
				        <li>
				        	<form method="post" action="/sza-webapp/korisnici/${sessionScope.korisnik.korisnickoIme}/">
				            	<button name="button" value="signout" class="btn btn-primary navbar-btn" id="odjavaRight">Odjava</button>
				          	</form>
				        </li>
			        </c:when>
			        <c:otherwise>
			        	<c:choose>
			        	    <c:when test="${url == 2}">			
				        		<li><div class="btn-toolbar">
									<button class="btn btn-primary navbar-btn" onclick="location.href='/sza-webapp/prijava/'"><span class="glyphicon glyphicon-log-in"></span> Prijava</button>
								</div></li>
				            </c:when>
				            <c:when test="${url == 1}">			
   			        			<li><div class="btn-toolbar">
									<button class="btn btn-primary navbar-btn" onclick="location.href='/sza-webapp/registracija/'"><span class="glyphicon glyphicon-user"></span> Registracija</button>
								</div></li>
				            </c:when>
          			        <c:otherwise>
          			            <li>
          			                <div class="btn-toolbar">
          			                    <button class="btn btn-primary navbar-btn" onclick="location.href='/sza-webapp/prijava/'"><span class="glyphicon glyphicon-log-in"></span> Prijava</button>
   			        			        <button class="btn btn-primary navbar-btn" onclick="location.href='/sza-webapp/registracija/'"><span class="glyphicon glyphicon-user"></span> Registracija</button>
   			        			    </div>
   			        			</li>
          			        </c:otherwise>
				        </c:choose>
			        </c:otherwise>
		        </c:choose>
			</ul>
    	</div>    
	</div>
</nav>