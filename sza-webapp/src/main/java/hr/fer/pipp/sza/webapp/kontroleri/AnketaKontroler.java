package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/ankete/")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnkete(@Context HttpServletRequest req) throws ServletException, IOException {
		List<Anketa> ankete;
		if (req.getSession().getAttribute("korisnik") == null) {
			ankete = DAOAnketa.getDAO().dohvatiAnkete(false); // nije logiran
		} else {
			ankete = DAOAnketa.getDAO().dohvatiAnkete(true); // logiran
		}
		req.setAttribute("ankete", ankete);
		req.setAttribute("url", 0);
		return Response.ok(new Viewable("/ankete")).build();
	}

	@GET
	@Path("/{idnazivanketa}")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("idnazivanketa") String idNazivAnketa) {
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0])); // dohvati
																											// po
																											// id-u
		if (anketa != null && req.getSession().getAttribute("korisnik") != null) { // ako
																					// je
																					// korisnik
																					// logiran,
																					// nije
																					// bitno
																					// je
																					// li
																					// anketa
																					// privatna
			req.setAttribute("anketa", anketa);
		} else if (anketa != null && !anketa.isJePrivatna()) { // ako korisnik
																// nije logiran,
																// onda anketa
																// mora biti
																// javna da bi
																// ju vidio
			req.setAttribute("anketa", anketa);
		}
		return Response.ok(new Viewable("/anketa")).build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response dodajAnketu(@Context HttpServletRequest req, @FormParam("nazivAnketa") String nazivAnketa,
			@FormParam("opisAnketa") String opisAnketa, @FormParam("aktivnaOd") String aktivnaOd,
			@FormParam("aktivnaDo") String aktivnaDo, @FormParam("brojPitanja") String brojPitanja)
					throws ParseException, ServletException, IOException {

		Map<String, String> greska = Util.provjeriFormuAnkete(nazivAnketa, opisAnketa, aktivnaOd, aktivnaDo,
				brojPitanja);

		if (greska.isEmpty()) {

			Anketa anketa = new Anketa();
			anketa.setNazivAnketa(nazivAnketa);
			anketa.setOpisAnketa(opisAnketa);
			anketa.setVrijemeIzrada(new Date());

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			Date datum = format.parse(aktivnaOd);
			anketa.setAktivnaOd(datum);

			datum = format.parse(aktivnaDo);
			anketa.setAktivnaDo(datum);

			anketa.setBrojPitanja(Integer.parseInt(brojPitanja));
			System.out.println(anketa.getBrojPitanja());
			System.out.println(anketa.getNazivAnketa());
			req.setAttribute("url", 1);

			// TODO spremiti anketu u bazu

		} else {
			Map<String, String> forma = new HashMap<>();
			forma.put("nazivAnketa", nazivAnketa);
			forma.put("opisAnketa", opisAnketa);
			forma.put("aktivnaOd", aktivnaOd);
			forma.put("aktivnaDo", aktivnaDo);
			forma.put("brojPitanja", brojPitanja);

			req.setAttribute("forma", forma);
			req.setAttribute("greska", greska);
			req.setAttribute("url", 2);
		}

		return prikaziAnkete(req);
	}
}
