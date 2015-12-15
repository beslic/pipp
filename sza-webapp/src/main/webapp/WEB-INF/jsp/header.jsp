<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <p class="navbar-brand"><b>Sustav za anktetiranje</b></p>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <ul class="nav navbar-nav navbar-left">
          <li><a href="/sza-webapp/ankete/">Ankete</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
        <c:when test="${sessionScope.korisnik != null}">
        <li>Prijavljen ${sessionScope.korisnik.ime}</li>
        </c:when>
        <c:otherwise>
        <li><a href="/sza-webapp/prijava/">Prijava</a></li>
        <li><a href="/sza-webapp/registracija/">Registracija</a></li>
        </c:otherwise>
        </c:choose>
       </ul>
    </div>
  </div>
</nav>