<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Rezultati: ${anketa.nazivAnketa}</title>
    <c:import url="/html/head.html" />
    <script type='text/javascript' src='/sza-webapp/js/jquery.canvasjs.min.js'></script>
</head>
<body>
    
    <jsp:include page="header.jsp" />
    
    <c:set var="ime" value="${anketa.idAnketa}-${anketa.nazivAnketa}"/>
    <c:set var="url" value="ankete/${fn:replace(ime, ' ', '-')}" />
    
    <div class="container">
        
        <div class="col-md-10">
            <h2>Rezultati ankete ${anketa.nazivAnketa}
                <c:if test="${!anketa.aktivna}">
                   <span class="glyphicon glyphicon-info-sign icon-info" 
                   data-toggle="popover" data-trigger="hover" title="Anketa nije aktivna!" 
                   data-content="Da biste mogli ispuniti anketu, anketa mora biti aktivna"></span>
                </c:if>
                <span class="pull-right">
                    <button id="rezultati" type="button" name="razultati" onclick="location.href='/sza-webapp/${url}/'" class="btn btn-info">Anketa</button>
                </span>
            </h2>
            <hr>
            <h2><small>${anketa.opisAnketa}</small></h2>
        </div>
        
        <c:forEach var="pitanje" items="${anketa.pitanja}" varStatus="p">
	       <div id="pitanje${p.index + 1}" class="col-md-8 col-md-offset-1">
	           <h3><small>${pitanje.rbrPitanje}.</small>  ${pitanje.textPitanje}</h3>
	           <hr>
	           <c:forEach var="odgovor" items="${pitanje.odgovor}">
	              <div class="col-md-12">
	                   <div class="col-md-5 col-md-offset-1">
	                       <div class="radio">
	                           <label>${odgovor.textOdgovor}</label>
	                       </div>
	                   </div>
	               </div>
	           </c:forEach>
	       </div>
        </c:forEach>
        <div class="col-md-10">
            <hr>
        </div>
        <div class="col-md-10">
            <div id="chartContainer"></div>
        </div>
    </div>
    
    <script type="text/javascript">
    
    $(document).ready(function(){
        $('[data-toggle="popover"]').popover({
            placement : 'right',
            trigger : 'hover'
        });
    });
    
    </script>
    
    <script type="text/javascript">
    
    $(function () {
        $("#chartContainer").CanvasJSChart('${data}');
    });
    
    </script>
    
    </body>
</html>