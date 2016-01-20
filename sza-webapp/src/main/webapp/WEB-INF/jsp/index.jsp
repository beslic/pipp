<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sustav za anketiranje</title>
    <c:import url="/html/head.html" />
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container" id="odvojiJumbotronOdHeadera">
	<div class="jumbotron">
		  <h1><span class="glyphicon glyphicon-list-alt"></span> Sustav za anketiranje</h1>
		  <p>Sustav za provođenje anketiranja putem web aplikacije i mobilne aplikacije</p>
  		  <p><b>Mobilna aplikacija </b><span class="glyphicon glyphicon-chevron-right"></span> kreiranje anketa</p> 
		  <p><b>Web aplikacija </b><span class="glyphicon glyphicon-chevron-right"></span> provođenje anketa</p> 
		  <p><a class="btn btn-primary btn-lg" href="#" role="button">Mobilna aplikacija</a></p>
	</div>
</div>
<hr>

<div class="container">

<div class="panel panel-primary">
  <div class="panel-heading"><b>Anketiranje</b></div>
  <div class="panel-body">
  	Anketiranje je posebna metoda prikupljanja podataka uz čiju 
	pomoć dolazimo do podataka o stavovima i mišljenjima anketiranih 
	ispitanika. Anketiranje se provodi postavljanjem točno određenih 
	pitanja određenoj grupi ljudi na koje oni odgovaraju jednim ili
	 više odgovora. Anketiranje koriste osobe ili tvrtke koje žele
	  provjeriti zadovoljstvo stranaka proizvodima, uslugama, otkriti
	   želje i potrebe klijenata, javno mišljenje stranaka o određenim
	    temama, itd. Razvojem novih tehnologija, prvenstveno telefona 
	    i interneta, dolazi do olakšanja provođenja anketiranja.
  </div>
</div>
</div>
<div class="container" id="positionCenter" style="width: 60%">

 <h2 class="topmargin">Članovi tima</h2>
 <hr>
 <br>    
  <table class="table table-striped">
    <thead>
      <tr>
        <th><b>Ime i prezime</b></th>
        <th><b>E-mail adresa</b></th>
        <th><b>GSM broj</b></th>
        
      </tr>
    </thead>   
			<tr>      
			<td>Petar Bešlić</td>
			<td>petar.beslic2@fer.hr</td>
			<td></td>
			</tr>
			<tr>
			<td>Andrea Kurek</td>
			<td>andrea.kurek@fer.hr</td>
			<td>0917311832</td>
			</tr>
			<tr>
			<td>Martin Mašić</td>
			<td>martin.masic@fer.hr</td>
			<td>0989560022</td>
			</tr>
			<tr>
			<td>Marko Plantić</td>
			<td>marko.plantic@fer.hr</td>
			<td>0912091208</td>
			</tr>
			<tr>
			<td>Marin Smoljanić</td>
			<td>marin.smoljanic@fer.hr</td>
			<td>0996902742</td>
			</tr>
			<tr>
			<td>Vedran Biđin</td>
			<td>vedran.bidin@fer.hr</td>
			<td>0919508901</td>
			</tr>
			<tr>
			<td>Mateo Glavičić</td>
			<td>mateo.glavicic@fer.hr</td>
			<td>0915179465</td>
			</tr>
			<tr>
			<td>Alen Hrga</td>
			<td>alen.hrga@fer.hr</td>
			<td>0919885554</td>
			</tr>
			<tr>
			<td>Pavao Križić</td>
			<td>pavao.krizic@fer.hr</td>
			<td>0919256321</td>
			</tr>  
      	</tbody>
	</table>
	<br>
	<hr>
</div>
    
<div id="footer">
	<h2><span class="glyphicon glyphicon-option-horizontal"></span></h2>	
	<p><span class="glyphicon glyphicon-copyright-mark"></span> Projekt iz programske potpore</>

</div>
    
    <script>
   		$('.datepicker').datepicker()
   		
	    $('.carousel').carousel({
	        interval: 5000 //changes the speed
	    })
    
  		function hideshow(which){
	  		if (!document.getElementById)
	  		return
	  		if (which.style.display=="block")
	  		which.style.display="none"
	  		else
	  		which.style.display="block"
  		}
    </script>

</body>
</html>
