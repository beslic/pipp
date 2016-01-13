<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Ankete</title>
    <c:import url="/html/head.html" />
</head>

<body>
    <jsp:include page="header.jsp" />
    
<div class="container" id="odvojiOdHeadera">
  <ul id="tabovi" class="nav nav-pills nav-justified">
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
          <h2>Ovdje ide popis javnih anketa</h2>
    </div>
    <c:if test="${sessionScope.korisnik != null}">
    
    <div id="moje-ankete" class="tab-pane fade">
          <h2>Ovdje ide popis privatnih anketa</h2>
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