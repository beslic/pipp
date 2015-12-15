<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <c:import url="/html/head.html" />
    <title>Korisnik</title>
</head>

<body>
    <jsp:include page="header.jsp" />
    <h2>Ovdje idu podaci o korisniku ${korisnickoime}</h2>
    <p>Ime:</p>
    <p>Prezime: </p>
    <p>itd</p>
</body>
</html>