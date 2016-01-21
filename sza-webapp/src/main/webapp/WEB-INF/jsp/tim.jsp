<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>O nama</title>
    <c:import url="/html/head.html" />
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">

<div class="col-md- 6 col-md-offset-3" id="positionCenter" style="width: 60%">
<h2 class="topmargin">Članovi tima</h2>
<hr>
<br>
<table class="table table-striped">
<thead>
<tr>
<th><b>Ime i prezime</b></th>
<th><b>E-mail adresa</b></th>
</tr>
</thead>
<tr>
<td>Petar Bešlić</td>
<td>petar.beslic2@fer.hr</td>
</tr>
<tr>
<td>Andrea Kurek</td>
<td>andrea.kurek@fer.hr</td>
</tr>
<tr>
<td>Martin Mašić</td>
<td>martin.masic@fer.hr</td>
</tr>
<tr>
<td>Marko Plantić</td>
<td>marko.plantic@fer.hr</td>
</tr>
<tr>
<td>Marin Smoljanić</td>
<td>marin.smoljanic@fer.hr</td>
</tr>
<tr>
<td>Vedran Biđin</td>
<td>vedran.bidin@fer.hr</td>
</tr>
<tr>
<td>Mateo Glavičić</td>
<td>mateo.glavicic@fer.hr</td>
</tr>
<tr>
<td>Alen Hrga</td>
<td>alen.hrga@fer.hr</td>
</tr>
<tr>
<td>Pavao Križić</td>
<td>pavao.krizic@fer.hr</td>
</tr>
</table>
<br>
<hr>
</div>
</div>

</body>
</html>

