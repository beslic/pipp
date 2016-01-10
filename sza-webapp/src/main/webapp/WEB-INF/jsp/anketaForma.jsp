<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	    <form class="form-horizontal" method="post">
	    
        <fieldset>
          <!-- Form Name -->
  		  <div class="col-md-9">
		   <h2>Kreiranje ankete</h2>
  		  </div>
                     
          <!-- Poll input-->
          <div class="form-group">
            <label id="usernamelabel" class="col-md-4 control-label" for="usernameinput">Ime ankete</label>
            <div class="col-md-4 <c:if test="${greska.nazivAnketa != null}">has-error has-feedback</c:if>">
              <input id="usernameinput" name="nazivAnketa" type="text" class="form-control input-md" value="${forma.nazivAnketa}" placeholder="Unesite naziv ankete" aria-describedby="errorstatus">
              <c:if test="${greska.nazivAnketa != null}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <label class="control-label" for="usernameinput">${greska.nazivAnketa}</label>
                <span id="errorstatus" class="sr-only">(error)</span>
              </c:if>
            </div>
          </div>
          <!-- Poll description-->
          <div class="form-group">
            <label id="passwordlabel" class="col-md-4 control-label" for="passwordinput">Opis ankete</label>
            <div class="col-md-4 <c:if test="${greska.opisAnketa != null}">has-error has-feedback</c:if>">
              <input id="passwordinput" name="opisAnketa" type="text" class="form-control input-md" placeholder="Unesite opis ankete" aria-describedby="errorstatus">
              <c:if test="${greska.opisAnketa != nul">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <label class="control-label" for="passwordinput">${greska.opisAnketa}</label>
                <span id="errorstatus" class="sr-only">(error)</span>
              </c:if>
            </div>
          </div>
        
          <!-- Buttons -->
 			<div class="form-group">
 			  <label class="col-md-4 control-label" for="signup"></label>
 			  <div class="col-md-4">
 			    <button id="register" type="submit" name="register" onclick="location.href='/sza-webapp/registracija/'" class="btn btn-success">Stvori anketu</button>
 			    <button id="reset" type="reset" name="reset" class="btn btn-warning">Poništi</button>
 			  </div>
 			</div>
		  </fieldset>
		</form>
	  </div>

</body>
</html>