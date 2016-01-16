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
            <h2>${anketa.nazivAnketa}</h2>
            <hr>
            <h2><small>${anketa.opisAnketa}</small></h2>
        </div>
        
        <c:forEach var="pitanje" items="${anketa.pitanja}">
            <div class="col-md-8 col-md-offset-1">
                <h3><small>${pitanje.rbrPitanje}.</small>  ${pitanje.textPitanje}</h3>
                <hr>
                <c:forEach var="odgovor" items="${pitanje.odgovor}">
                    <div class="col-md-7 col-md-offset-1">
                        <div class="radio">
                            <label>
                                <input type="radio" name="${pitanje.idPitanje}" id="${odgovor.idOdgovor}" value="${odgovor.idOdgovor}">
                                    ${odgovor.textOdgovor}
                            </label>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        
    </div>
    
</body>
</html>