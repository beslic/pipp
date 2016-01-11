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

<header id="myCarousel" class="carousel slide">

        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner">
            <div class="item active">
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Prva anketa');"></div>
                <div class="carousel-caption">
                    <h2>Naslov prve ankete</h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Druga anketa');"></div>
                <div class="carousel-caption">
                    <h2>Naslov druge ankete</h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Treca anketa');"></div>
                <div class="carousel-caption">
                    <h2>Ilko Brnetic</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>
    
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
