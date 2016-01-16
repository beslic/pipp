<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Ankete</title>
    <c:import url="/html/head.html" />
    <c:if test="${sessionScope.korisnik != null}">
        <link rel="stylesheet" href="/sza-webapp/css/datepicker.css">
        <link rel="stylesheet" href="/sza-webapp/css/datepicker3.css">
        <script type='text/javascript' src='/sza-webapp/js/bootstrap-datepicker.js'></script>
    </c:if>
</head>

<body>
    <jsp:include page="header.jsp" />
    
<div class="container" id="odvojiOdHeadera">
<!--   <ul id="tabovi" class="nav nav-tabs nav-justified"> -->
<!--     <li class="active"><a data-toggle="tab" href="#javne-ankete">Javne ankete</a></li> -->
<%--     <c:if test="${sessionScope.korisnik != null}"> --%>
<!-- 	    <li><a data-toggle="tab" href="#moje-ankete">Moje ankete</a></li> -->
<%--     </c:if> --%>
    
<%--       <c:if test="${sessionScope.korisnik.razinaPrava < 2}"> --%>
<!--             <li><a data-toggle="tab" href="#nova-anketa">Nova anketa</a></li> -->
<%--    	 </c:if> --%>
    	
<!--   </ul> -->

  <div class="tab-content">
    <div id="javne-ankete" class="tab-pane fade in active">
        <jsp:include page="prikazAnketa.jsp" />
    </div>
    <c:if test="${sessionScope.korisnik != null}">
        <div id="moje-ankete" class="tab-pane fade">
            <div class="col-md-10">
                <h2>Moje ankete</h2>
                <hr>
            </div>
            <c:if test="${ankete == null}">
                <h3>Nista napravili niti jednu anketu</h3>
            </c:if>
            <jsp:include page="prikazAnketa.jsp" />
        </div>
        <c:if test="${sessionScope.korisnik.razinaPrava < 2}">
        <div id="nova-anketa" class="tab-pane fade">
            <jsp:include page="anketaForma.jsp" />
        </div>
    </c:if>
    </c:if>
  </div>
</div>
<script type="text/javascript">
  $('#tabovi a[href="#${tab}"]').tab('show')
</script>

</body>
</html>