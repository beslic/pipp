<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Ankete</title>
    <c:import url="/html/head.html" />
    <link rel="stylesheet" href="/sza-webapp/css/datepicker.css">
    <link rel="stylesheet" href="/sza-webapp/css/datepicker3.css">
    <script type='text/javascript' src='/sza-webapp/js/bootstrap-datepicker.js'></script>
</head>

<body>
    <jsp:include page="header.jsp" />
    
<div class="container" id="odvojiOdHeadera">
  <ul id="tabovi" class="nav nav-tabs nav-justified">
    <li class="active"><a data-toggle="tab" href="#javne-ankete">Javne ankete</a></li>
    <c:if test="${sessionScope.korisnik != null}">
	    <li><a data-toggle="tab" href="#moje-ankete">Moje ankete</a></li>
    </c:if>
    
      <c:if test="${sessionScope.korisnik.razinaPrava < 2}">
            <li><a data-toggle="tab" href="#nova-anketa">Nova anketa</a></li>
   	 </c:if>
    	
  </ul>

  <div class="tab-content">
    <div id="javne-ankete" class="tab-pane fade in active">
        <c:if test="${javneAnkete == null}">
            <h3>Nema javno dostupnih anketa</h3>
        </c:if>
        <c:forEach var="anketa" items="${javneAnkete}">
            <c:set var="uri" value="${anketa.idAnketa}-${anketa.nazivAnketa}"/>
            <h3><a href="/sza-webapp/ankete/${url}${fn:replace(uri, ' ', '-')}/">${anketa.nazivAnketa}</a></h3>
        </c:forEach>
    </div>
    <c:if test="${sessionScope.korisnik != null}">
        <div id="moje-ankete" class="tab-pane fade">
            <c:if test="${privatneAnkete == null}">
                <h3>Nemate još nijednu anketu</h3>
            </c:if>
            <c:forEach var="anketa" items="${javneAnkete}">
                <p>${anketa.nazivAnketa}</p>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${sessionScope.korisnik.razinaPrava < 2}">
        <div id="nova-anketa" class="tab-pane fade">
            <jsp:include page="anketaForma.jsp" />
        </div>
    </c:if>
  </div>
</div>
<script type="text/javascript">
  $('#tabovi a[href="#${tab}"]').tab('show')
</script>

</body>
</html>