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
  <ul class="nav nav-tabs">
    <li <c:if test="${url == 0}"> class="active"</c:if>><a data-toggle="tab" href="#javne-ankete">Javne ankete</a></li>
    <c:if test="${sessionScope.korisnik != null}">
	    <li <c:if test="${url == 1}"> class="active"</c:if>><a data-toggle="tab" href="#moje-ankete">Moje ankete</a></li>
    </c:if>
    
      <c:if test="${sessionScope.korisnik.razinaPrava < 2}">
            <li <c:if test="${url == 2}"> class="active"</c:if>><a data-toggle="tab" href="#nova-anketa">Nova ankete</a></li>
   	 </c:if>
    	
  </ul>

  <div class="tab-content">
    <div id="javneAnkete" class="tab-pane fade <c:if test="${url == 0}">in active</c:if>">
          <h2>Ovdje ide popis javnih anketa</h2>
    </div>
    <c:if test="${sessionScope.korisnik != null}">
    
    <div id="mojeAnkete" class="tab-pane fade <c:if test="${url == 1}">in active</c:if>">
          <h2>Ovdje ide popis privatnih anketa</h2>
    </div>
    </c:if>
    
    <c:if test="${sessionScope.korisnik.razinaPrava < 2}">
    
    <div id="novaAnketa" class="tab-pane fade <c:if test="${url == 2}">in active</c:if>">
          <jsp:include page="anketaForma.jsp" />
    </div>
    </c:if>
  </div>
</div>

	<script>
	$(document).ready(function() {
		 $('#dateRangePickerFrom')
	     .datepicker({
	         format: 'mm/dd/yyyy',
	         startDate: '01/01/2010',
	         endDate: '12/30/2020'
	     })
	     .on('changeDate', function(e) {
	         // Revalidate the date field
	         $('#dateRangeForm').formValidation('revalidateField', 'date');
     });
	 
    $('#dateRangePickerTo')
        .datepicker({
            format: 'mm/dd/yyyy',
            startDate: '01/01/2010',
            endDate: '12/30/2020'
        })
        .on('changeDate', function(e) {
            // Revalidate the date field
            $('#dateRangeForm').formValidation('revalidateField', 'date');
  	 });

    $('#dateRangeForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                    notEmpty: {
                        message: 'The date is required'
                    },
                    date: {
                        format: 'MM/DD/YYYY',
                        min: '01/01/2010',
                        max: '12/30/2020',
                        message: 'The date is not a valid'
                    }
                }
            }
        }
    });
});
</script>
   	
</body>
</html>