
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<form class="form-horizontal" method="post">

		<fieldset>
			<!-- Form Name -->
			<div class="col-md-10">
				<h2>Kreiranje ankete</h2>
				<hr>
				<br>
			</div>

			<!-- Poll input-->
			<div class="form-group">
				<label id="usernamelabel" class="col-md-4 control-label"
					for="usernameinput">Ime ankete</label>
				<div
					class="col-md-6 <c:if test="${greska.nazivAnketa != null}">has-error has-feedback</c:if>">
					<input id="usernameinput" name="nazivAnketa" type="text"
						class="form-control input-md" value="${forma.nazivAnketa}"
						placeholder="Unesite naziv ankete" aria-describedby="errorstatus">
					<c:if test="${greska.nazivAnketa != null}">
						<span class="glyphicon glyphicon-remove form-control-feedback"
							aria-hidden="true"></span>
						<label class="control-label" for="usernameinput">${greska.nazivAnketa}</label>
						<span id="errorstatus" class="sr-only">(error)</span>
					</c:if>
				</div>
			</div>

			<!-- Poll description-->
			<div class="form-group">
				<label id="opis" class="col-md-4 control-label"
					for="opisinput">Opis ankete</label>
				<div class="col-md-6">
					<textarea id="opisinput" name="opisAnketa"
						class="form-control input-md col-md-6" rows="3"
						placeholder="Unesite opis ankete" aria-describedby="errorstatus"></textarea>
				</div>
			</div>

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-md-4 control-label">Broj pitanja</label> -->

<!-- 				<div class="col-md-4"> -->
<!-- 					<select class="form-control" name="brojPitanja"> -->
<%-- 						<c:forEach var="i" begin="1" end="10"> --%>
<%-- 							<option value="${i}">${i}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<div class="form-group">
				<label id="privatna" class="col-md-4 control-label"
					for="privatnainput">Privatna anketa</label>
				<div
					class="col-md-6 <c:if test="${greska.privatna != null}">has-error has-feedback</c:if>">
					<input id="privatnainput" name="privatna" class="input-md"
						type="checkbox" value="privatna"
						<c:if test="${forma.privatna eq '1'}">checked</c:if>>
						<span class="help-block">Označite ukoliko ne želite da vaša anketa bude javno dostupna na Intenretu</span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label"></label>
				<div class="col-md-6">
					<div class="input-group input-append date">
						<b>Datum provodenja ankete</b>
					</div>
					<hr>
				</div>
			</div>


			<div class="form-group">
				<label class="col-md-4 control-label">Od</label>
				<div
					class="col-md-3 <c:if test="${greska.aktivnaOd != null}">has-error has-feedback</c:if>">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input id="from" type="text" class="form-control input-md-4"
							name="aktivnaOd" value="${forma.aktivnaOd}" /> <span
							class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<c:if test="${greska.aktivnaOd != null}">
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<label class="control-label" for="from">${greska.aktivnaOd}</label>
						<label class="control-label" for="from">${greska.aktivnaOdKron}</label>
					</c:if>
				</div>
			</div>


			<div class="form-group">
				<label class="col-md-4 control-label">Do</label>
				<div
					class="col-md-3 <c:if test="${greska.aktivnaDo != null}">has-error has-feedback</c:if>">
					<div class="input-group input-append date" id="dateRangePickerTo">
						<input id="to" type="text" class="form-control input-md"
							name="aktivnaDo" value="${forma.aktivnaDo}" /> <span
							class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<c:if test="${greska.aktivnaDo != null}">
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<label class="control-label" for="to">${greska.aktivnaDo}</label>
					</c:if>
				</div>
			</div>
			
			<div class="form-group">
			     <label class="col-md-4 control-label"></label>
			     <div class="col-md-6">
                    <div class="input-group input-append">
                        <br>
                        <b>Dodavanje pitanja i odgovora</b>
                    </div>
                    <hr>
                    <button type="button" class="btn btn-primary pull-right" onclick="dodajPitanje()">Dodaj pitanje</button>
                </div>
            </div>
            
            <div class="pitanja">
                
            </div>
            
            
			<!-- Buttons -->
			<div class="form-group">
				<div class="col-md-10">
				    <hr>
				    <div class="pull-right">
					<button id="register" type="submit" name="register"
						onclick="location.href='/sza-webapp/registracija/'"
						class="btn btn-success">Napravi anketu</button>
					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Poništi</button>
					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<script>
    $(document).ready(function() {
         $('#dateRangePickerFrom')
         .datepicker({
             format: 'dd/mm/yyyy',
             startDate: '01/01/2010',
             endDate: '31/12/2020'
         })
         .on('changeDate', function(e) {
             // Revalidate the date field
             $('#dateRangeForm').formValidation('revalidateField', 'date');
     });
     
    $('#dateRangePickerTo')
        .datepicker({
            format: 'dd/mm/yyyy',
            startDate: '01/01/2010',
            endDate: '31/12/2020'
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
                        message: 'Potrebno je odabrati datum'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        min: '01/01/2010',
                        max: '30/12/2020',
                        message: 'Datum nije ispravan'
                    }
                }
            }
        }
    });
});
    
    function dodajPitanje() {
		console.log("Dodajem pitanje");
		var divPitanja = $(".pitanja");
		var brojPitanja = divPitanja.children().length;
		divPitanja.append(
			'<div class="row">' +
			    '<div class="form-group">' +
			        '<label class="col-md-4 control-label" for="pitanje' + (brojPitanja + 1) + '">Pitanje broj ' + (brojPitanja + 1) + '</label>' +
			        '<div class="col-md-6">' +
                        '<input type="text" id="pitanje' + (brojPitanja + 1) + '" name="pitanje' + (brojPitanja + 1) + '" class="form-control input-md" placeholder="Tekst ' + (brojPitanja + 1) + '. pitanja">' +
                    '</div>' +
                '</div>' +
                '<div id="odgovori' + (brojPitanja + 1) + '"></div>' +
                '<div class="form-group">' +
                    '<div class="col-md-offset-4 col-md-6">' +
                        '<button type="button" class="btn btn-info" onclick="dodajOdgovor(' + (brojPitanja + 1) + ')">Dodaj odgovor</button>' +
                    '</div>' +
                '</div>' +
            '</div>');
	}

	function dodajOdgovor(pitanje) {
		console.log("Dodajem odgovor za " + pitanje);
// 		var divOdgovora = $(".pitanja > :nth-child(" + pitanje + ")");
        var liOdgovor = $("#odgovori" + pitanje);
        var brojOdgovora = liOdgovor.children().length;
        liOdgovor.append(
        		'<div class="form-group">' +
                    '<label class="col-md-5 control-label" for="pitanje' + (pitanje) + '-odgovor' + (brojOdgovora + 1) + '">Odgovor ' + (brojOdgovora + 1) + '</label>' +
                    '<div class="col-md-5">' +
                        '<input type="text" id="pitanje' + (pitanje) + '-odgovor' + (brojOdgovora + 1) + '" name="pitanje' + (brojOdgovora + 1) + '" class="form-control input-md" placeholder="Tekst ' + (brojOdgovora + 1) + '. odgovora">' +
                    '</div>' +
                '</div>');
	}
	
	function spremi()
	{
		// spakiraj sve u neki object i onda post
	}
</script>

