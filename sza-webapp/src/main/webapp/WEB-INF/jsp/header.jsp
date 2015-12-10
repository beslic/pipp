<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <p class="navbar-brand"><b>Sustav za anktetiranje</b></p>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Korisnicko ime">
          <input type="text" class="form-control" placeholder="Lozinka">
          
        </div>
        <button type="submit" class="btn btn-default" onclick="location.href='/sza-webapp/registracija/'">Prijavite se</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Ankete</a></li>
        <li><a href="/sza-webapp/prijava/">Prijava</a></li>
        <li><a href="/sza-webapp/registracija/">Registracija</a></li>
           
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>