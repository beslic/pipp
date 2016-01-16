
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
				<label id="usernamelabel" class="col-md-3 control-label"
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
				<label id="opis" class="col-md-3 control-label"
					for="opisinput">Opis ankete</label>
				<div class="col-md-6">
					<textarea id="opisinput" name="opisAnketa"
						class="form-control input-md col-md-6" rows="3"
						placeholder="Unesite opis ankete" aria-describedby="errorstatus"></textarea>
				</div>
			</div>

			<div class="form-group">
				<label id="privatna" class="col-md-3 control-label"
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
				<label class="col-md-3 control-label"></label>
				<div class="col-md-6">
					<div class="input-group input-append date">
						<b>Datum provođenja ankete</b>
					</div>
					<hr>
				</div>
			</div>


			<div class="form-group">
				<label class="col-md-3 control-label">Od</label>
				<div
					class="col-md-3 <c:if test="${greska.aktivnaOd != null || greska.kron != null}">has-error has-feedback</c:if>">
					<div class="input-group input-append date" id="dateRangePickerFrom">
						<input id="from" type="text" class="form-control input-md-4"
							name="aktivnaOd" value="${forma.aktivnaOd}" /> <span
							class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<c:if test="${greska.aktivnaOd != null}">
						<label class="control-label" for="from">${greska.aktivnaOd}</label>
					</c:if>
				</div>
			</div>


			<div class="form-group">
				<label class="col-md-3 control-label">Do</label>
				<div
					class="col-md-3 <c:if test="${greska.aktivnaDo != null || greska.kron != null}">has-error has-feedback</c:if>">
					<div class="input-group input-append date" id="dateRangePickerTo">
						<input id="to" type="text" class="form-control input-md" name="aktivnaDo" value="${forma.aktivnaDo}" /> <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<c:if test="${greska.aktivnaDo != null || greska.kron != null}">
						<label class="control-label" for="to">${greska.aktivnaDo}${greska.kron}</label>
					</c:if>
				</div>
			</div>

		   <div id="pitanja">
		    <c:if test="${forma.pitanja != null}">
          <c:forEach var="entry" items="${forma.pitanja}" varStatus="p">
          <c:set var="pid" value="${p.index + 1}" />
		      <div id="pitanje${pid}"><div class="form-group"><div class="col-md-6 col-md-offset-3"><hr><b>Pitanje</b><br></div></div>
            <div class="form-group div-odgovor">
              <div class="col-md-6 col-md-offset-3">
                <input type="text" id="pitanje${pid}" name="pitanje${pid}" class="form-control input-md" placeholder="Tekst pitanja" value="${forma[entry.key]}">
              </div>
              <div class="col-md-3 div-rm-btn">
                <button type="button" class="btn btn-danger" onclick="obrisiPitanje(${pid})" data-toggle="tooltip" data-placement="top" title="Obriši pitanje"><span class="glyphicon glyphicon-minus"></span></button>
              </div>
            </div>
            <div class="form-group">
              <div class="col-md-6 col-md-offset-3">
                <b>Odgovori</b><span><button type="button" class="btn btn-success pull-right" onclick="dodajOdgovor(${pid})" data-toggle="tooltip" data-placement="top" title="Dodaj novi odgovor"><span class="glyphicon glyphicon-plus"></span></button></span>
              </div>
            </div>
            <div id="odgovori${pid}">
              <c:forEach var="odg" items="${entry.value}" varStatus="o">
              <c:set var="oid" value="${o.index + 1}" />
                <div id="pitanje${pid}-odgovor${oid}" name="pitanje${pid}-odgovor${oid}" class="form-group div-odgovor">
                    <div class="col-md-4 col-md-offset-4">
                        <input type="text" name="pitanje${pid}-odgovor${oid}" name="pitanje${pid}-odgovor${oid}" class="form-control input-md" placeholder="Tekst odgovora" value="${odg}">
                    </div>
                    <div class="col-md-1 div-rm-btn">
                        <button type="button" class="btn btn-danger" onclick="obrisiOdgovor(${pid}, ${oid})" data-toggle="tooltip" data-placement="top" title="Obriši odgovor"><span class="glyphicon glyphicon-minus"></span></button>
                    </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </c:forEach>
		    </c:if>
      </div>

			<div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-6">
                    <hr>
                    <button type="button" class="btn btn-success pull-right" onclick="dodajPitanje()" data-toggle="tooltip" data-placement="top" title="Dodaj novo pitanje">Dodaj pitanje</button>
                </div>
            </div>

			<!-- Buttons -->
			<div class="form-group">
				<div class="col-md-10">
				    <hr>
					<button id="register" type="submit" name="register"
						onclick="location.href='/sza-webapp/registracija/'"
						class="btn btn-success">Napravi anketu</button>
					<button id="reset" type="reset" name="reset"
						class="btn btn-warning">Poništi</button>
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
});

    function dodajPitanje() {
		console.log("Dodajem pitanje");
		var divPitanja = $("#pitanja");
		var brojPitanja = divPitanja.children().length + 1;
		divPitanja.append(
		    '<div id="pitanje' + brojPitanja + '"><div class="form-group"><div class="col-md-6 col-md-offset-3"><hr><b>Pitanje</b><br></div></div>' +
				'<div class="form-group div-odgovor">' +
                    '<div class="col-md-6 col-md-offset-3">' +
                        '<input type="text" id="pitanje' + brojPitanja + '" name="pitanje' + brojPitanja + '" class="form-control input-md" placeholder="Tekst pitanja">' +
                    '</div>' +
                    '<div class="col-md-3 div-rm-btn">' +
                        '<button type="button" class="btn btn-danger" onclick="obrisiPitanje(' + brojPitanja + ')" data-toggle="tooltip" data-placement="top" title="Obriši pitanje"><span class="glyphicon glyphicon-minus"></span></button>' +
                    '</div>' +
                '</div>' +
                '<div class="form-group">' +
                    '<div class="col-md-6 col-md-offset-3">' +
                        '<b>Odgovori</b><span><button type="button" class="btn btn-success pull-right" onclick="dodajOdgovor(' + brojPitanja + ')" data-toggle="tooltip" data-placement="top" title="Dodaj novi odgovor"><span class="glyphicon glyphicon-plus"></span></button></span>' +
                    '</div>' +
                '</div>' +
                '<div id="odgovori' + brojPitanja + '">' +
                '</div>' +
            '</div>');
	}

	function dodajOdgovor(pitanje) {
		console.log("Dodajem odgovor za " + pitanje);
        var liOdgovor = $("#odgovori" + pitanje);
        var brojOdgovora = liOdgovor.children().length + 1;
        liOdgovor.append(
        		'<div id="pitanje' + pitanje + '-odgovor' + brojOdgovora + '" name="pitanje' + pitanje + '-odgovor' + brojOdgovora + '" class="form-group div-odgovor">' +
                    '<div class="col-md-4 col-md-offset-4">' +
                        '<input type="text" name="pitanje' + pitanje + '-odgovor' + brojOdgovora + '" name="pitanje' + pitanje + '-odgovor' + brojOdgovora + '" class="form-control input-md" placeholder="Tekst odgovora">' +
                    '</div>' +
                    '<div class="col-md-1 div-rm-btn">' +
                        '<button type="button" class="btn btn-danger" onclick="obrisiOdgovor(' + pitanje + ', ' + brojOdgovora + ')" data-toggle="tooltip" data-placement="top" title="Obriši odgovor"><span class="glyphicon glyphicon-minus"></span></button>' +
                    '</div>' +
                '</div>');
	}

	function obrisiPitanje(pitanje) {
		console.log("Brisem pitanje " + pitanje);
		$("#pitanje" + pitanje).remove();
	}

    function obrisiOdgovor(pitanje, odgovor) {
        console.log("Brisem odgovor " + odgovor + " na pitanje " + pitanje);
        $("#pitanje" + pitanje + "-odgovor" + odgovor).remove();
    }

	function spremi()
	{
		// spakiraj sve u neki object i onda post
	}
</script>
