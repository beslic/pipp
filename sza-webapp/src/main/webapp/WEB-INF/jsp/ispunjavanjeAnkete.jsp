<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <c:import url="/html/head.html" />
    <title>Anketa: ${anketa.nazivAnketa}</title>
</head>
<body>
    
    <jsp:include page="header.jsp" />
    
    <div class="container">
        
        <div class="col-md-10">
            <h2>${anketa.nazivAnketa}
                <c:set var="ime" value="${anketa.idAnketa}-${anketa.nazivAnketa}"/>
                <c:set var="url" value="ankete/${fn:replace(ime, ' ', '-')}" />
                <span class="pull-right">
                    <c:if test="${!anketa.aktivna && (sessionScope.korisnik.razinaPrava == 0 || (sessionScope.korisnik.id == anketa.vlasnik.id))}">
                        <c:set var="url" value="korisnici/${sessionScope.korisnik.korisnickoIme}/${url}" />
                        <button id="izmijeni" type="button" name="izmijeni" onclick="location.href='/sza-webapp/${url}/izmijeni/'" class="btn btn-info">Izmijeni anketu</button>
                    </c:if>
                    <button id="rezultati" type="button" name="razultati" onclick="location.href='/sza-webapp/${url}/rezultati/'" class="btn btn-info">Vidi rezultate</button>
                </span>
            </h2>
            <hr>
            <h2><small>${anketa.opisAnketa}
	            <c:if test="${!anketa.aktivna}">
	                <span class="label label-warning pull-right">Anketa nije aktivna</span>
	            </c:if>
            </small></h2>
        </div>
        
        <form action="" method="post">
	        <c:forEach var="pitanje" items="${anketa.pitanja}" varStatus="p">
	            <div id="pitanje${p.index + 1}" class="col-md-8 col-md-offset-1">
	                <h3><small>${pitanje.rbrPitanje}.</small>  ${pitanje.textPitanje}</h3>
	                <hr>
	                <c:forEach var="odgovor" items="${pitanje.odgovor}">
	                   <div class="col-md-12 odg odg-line">
		                    <div class="col-md-5 col-md-offset-1">
		                        <div class="radio">
		                            <label>
		                              <c:if test="${anketa.aktivna}">
		                                <input type="radio" name="${pitanje.idPitanje}" id="${odgovor.idOdgovor}" value="${odgovor.idOdgovor}">
		                              </c:if>
		                                    ${odgovor.textOdgovor}
		                            </label>
		                        </div>
		                    </div>
	                    </div>
	                </c:forEach>
	            </div>
	        </c:forEach>
		    <div class="col-md-10">
		        <hr>
		        <c:if test="${anketa.aktivna}">
	               <span class="pull-right">
	                   <button id="napravi" type="submit" name="spremi" class="btn btn-success">Spremi</button>
	                   <button id="reset" type="reset" name="reset" class="btn btn-warning col-md-offset-">Poništi</button>
	               </span>
	            </c:if>
	        </div>
        </form>
        
    </div>
    
    <script type="text/javascript">
    $("#reset").click(function reset() {
    	$('input:radio').attr('checked', false);
    	$('#napravi').attr('disabled', true);
    });
    var size = $('div[id*=pitanje]').length;
    $('input:radio').change(function () {
    	if (size == $('input:radio:checked').length) {
    		$("#napravi").attr('disabled', false);
    	}
    });
    $('.odg').click(function() {
        $(this).find('input:radio')[0].checked = true; 
    });
    </script>
    
</body>
</html>