<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Anketari</title>
    <c:import url="/html/head.html" />
</head>

<body>
    <jsp:include page="header.jsp" />
    <div class="container">
    <br>
      <div class="container">
          <div class="row">
            <div class="col-md-2"></div>
              <div class="col-md-6">
                <h2>Anketari</h2>
                <hr></hr>
	          <c:forEach var="anketar" items="${anketari}">
	            <p class="lead" style="display: inline; color: #337AB7;">${anketar.korisnickoIme}</p>
	            <p>${anketar.ime}&nbsp;${anketar.prezime}</p>
	          </c:forEach>
              </div>
            </div>
      </div>
    </div>
</body>
</html>