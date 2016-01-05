<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Postavke - ${korisnik.korisnickoIme}</title>
    <c:import url="/html/head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Ovdje idu postavke korisnika ${korisnik.korisnickoIme}</h2>
</body>
</html>