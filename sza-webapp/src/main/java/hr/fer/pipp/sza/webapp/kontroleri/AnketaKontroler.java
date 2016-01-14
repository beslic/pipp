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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;

import com.google.gson.Gson;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/ankete/")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnkete(@Context HttpServletRequest req) throws ServletException, IOException {
		List<Anketa> javne = DAOAnketa.getDAO().dohvatiJavneAnkete();
		if (!javne.isEmpty()) {
			req.setAttribute("javneAnkete", javne);
		}
		Korisnik k = (Korisnik) req.getSession().getAttribute("korisnik");
		if (k != null) {
			List<Anketa> privatne = DAOAnketa.getDAO().dohvatiPrivatneAnkete(k);
			if (!privatne.isEmpty()) {
				req.setAttribute("privatneAnkete", privatne);
			}
		}
		if (req.getAttribute("tab") == null) {
			req.setAttribute("tab", "javne-ankete");
		}
		return Response.ok(new Viewable("/ankete")).build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response dodajAnketu(@Context HttpServletRequest req, @FormParam("nazivAnketa") String nazivAnketa,
			@FormParam("opisAnketa") String opisAnketa, @FormParam("aktivnaOd") String aktivnaOd,
			@FormParam("aktivnaDo") String aktivnaDo, @FormParam("brojPitanja") String brojPitanja,
			@FormParam("privatna") String privatna, @Context UriInfo uri)
					throws ParseException, ServletException, IOException {

		Map<String, String> greska = Util.provjeriFormuAnkete(nazivAnketa, opisAnketa, aktivnaOd, aktivnaDo,
				brojPitanja);

		if (greska.isEmpty()) {

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			Anketa anketa = new Anketa();
			anketa.setNazivAnketa(nazivAnketa);
			anketa.setOpisAnketa(opisAnketa);
			anketa.setVrijemeIzrada(new Date());
			anketa.setAktivnaOd(format.parse(aktivnaOd));
			anketa.setAktivnaDo(format.parse(aktivnaDo));
			anketa.setBrojPitanja(Integer.parseInt(brojPitanja));
			anketa.setVlasnik((Korisnik) req.getSession().getAttribute("korisnik"));
			anketa.setJePrivatna(("privatna".equals(privatna)) ? true : false);

			DAOAnketa.getDAO().spremiAnketu(anketa);

			req.setAttribute("tab", "moje-ankete");

		} else {
			Map<String, String> forma = new HashMap<>();
			forma.put("nazivAnketa", nazivAnketa);
			forma.put("opisAnketa", opisAnketa);
			forma.put("aktivnaOd", aktivnaOd);
			forma.put("aktivnaDo", aktivnaDo);
			forma.put("brojPitanja", brojPitanja);
			if ("privatna".equals(privatna)) {
				forma.put("privatna", "1");
			}

			req.setAttribute("forma", forma);
			req.setAttribute("greska", greska);
			req.setAttribute("tab", "nova-anketa");
		}

		return Response.seeOther(UriBuilder.fromUri(uri.getRequestUri().toString()).build()).build();
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String prikaziAnketeKaoJSON(@Context HttpServletRequest req) {
		List<Anketa> javne = DAOAnketa.getDAO().dohvatiJavneAnkete();
		Gson gson = new Gson();
		return gson.toJson(javne.get(0));
	}

	@GET
	@Path("/{id-naziv}")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNazivAnketa) {
		if (idNazivAnketa == null || idNazivAnketa.length() == 0) {
			return Response.ok(new Viewable("/anketa")).build();
		}
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0]));
		// TODO nesto zapoceto, ne znam sta...
		return Response.ok(new Viewable("/anketa")).build();
	}
}
