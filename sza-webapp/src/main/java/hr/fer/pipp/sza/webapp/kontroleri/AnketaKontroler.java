package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.modeli.Odgovor;
import hr.fer.pipp.sza.webapp.modeli.Pitanje;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/ankete/")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnkete(@Context HttpServletRequest req) throws ServletException, IOException {
		Korisnik k = (Korisnik) req.getSession().getAttribute("korisnik");
		List<Anketa> privatne = null;
		if (k != null) {
			privatne = k.getAnketa();
			if (!privatne.isEmpty()) {
				req.setAttribute("privatneAnkete", privatne);
			}
			if (req.getAttribute("tab") == null) {
				req.setAttribute("tab", "moje-ankete");
			}
		}
		List<Anketa> javne = DAOAnketa.getDAO().dohvatiJavneAnkete();
		if (!javne.isEmpty()) {
			req.setAttribute("javneAnkete", javne);
		}
		return Response.ok(new Viewable("/ankete")).build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response dodajAnketu(@Context HttpServletRequest req, @Context UriInfo uri,
			MultivaluedMap<String, String> form) throws ParseException, ServletException, IOException {

		String nazivAnketa = form.getFirst("nazivAnketa");
		String opisAnketa = form.getFirst("opisAnketa");
		String aktivnaOd = form.getFirst("aktivnaOd");
		String aktivnaDo = form.getFirst("aktivnaDo");
		String privatna = form.getFirst("privatna");

		Map<String, String> greska = Util.provjeriFormuAnkete(nazivAnketa, opisAnketa, aktivnaOd, aktivnaDo);

		Set<String> pitanjaId = new LinkedHashSet<>();
		Set<String> odgovoriId = new LinkedHashSet<>();

		for (String s : form.keySet()) {
			if (s.matches("pitanje[0-9]+")) {
				pitanjaId.add(s);
			}
			if (s.matches("pitanje[0-9]+-odgovor[0-9]+")) {
				odgovoriId.add(s);
			}
		}

		if (greska.isEmpty()) {

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			Anketa anketa = new Anketa();

			int brojPitanja = pitanjaId.size();

			List<Pitanje> pitanja = new ArrayList<>();
			for (String p : pitanjaId) {
				String pit = form.getFirst(p);
				if (pit == null || pit.length() == 0) {
					continue;
				}
				Pitanje pitanje = new Pitanje();
				List<Odgovor> odgovori = new ArrayList<>();
				for (String o : odgovoriId) {
					String odg = form.getFirst(o);
					if (odg == null || odg.length() == 0) {
						continue;
					}
					Odgovor odgovor = new Odgovor();
					odgovor.setRbrOdgovor(Integer.parseInt(o.split("-")[1].replaceFirst("odgovor", "")));
					odgovor.setTextOdgovor(odg);
					odgovor.setPitanje(pitanje);
					odgovori.add(odgovor);
				}
				Collections.sort(odgovori, (o1, o2) -> Integer.compare(o1.getRbrOdgovor(), o2.getRbrOdgovor()));
				pitanje.setAnketa(anketa);
				pitanje.setOdgovor(odgovori);
				pitanje.setRbrPitanje(Integer.parseInt(p.replaceFirst("pitanje", "")));
				pitanje.setTextPitanje(pit);
				pitanja.add(pitanje);
			}
			Collections.sort(pitanja, (p1, p2) -> Integer.compare(p1.getRbrPitanje(), p2.getRbrPitanje()));

			Date d = new Date();
			anketa.setNazivAnketa(nazivAnketa);
			anketa.setOpisAnketa(opisAnketa);
			anketa.setVrijemeIzrada(d);
			anketa.setAktivnaOd(format.parse(aktivnaOd));
			anketa.setAktivnaDo(format.parse(aktivnaDo));
			anketa.setAktivna(Util.provjeraAktivnosti(anketa, d));
			anketa.setBrojPitanja(brojPitanja);
			anketa.setVlasnik((Korisnik) req.getSession().getAttribute("korisnik"));
			anketa.setJePrivatna(("privatna".equals(privatna)) ? true : false);
			anketa.setPitanja(pitanja);

			DAOAnketa.getDAO().spremiAnketu(anketa);
			return Response.seeOther(UriBuilder.fromUri(uri.getRequestUri().toString()).build()).build();

		} else {
			Map<String, Object> forma = new HashMap<>();
			forma.put("nazivAnketa", nazivAnketa);
			forma.put("opisAnketa", opisAnketa);
			forma.put("aktivnaOd", aktivnaOd);
			forma.put("aktivnaDo", aktivnaDo);
			if (privatna != null) {
				forma.put("privatna", "1");
			}

			Map<String, List<String>> fPitanja = new LinkedHashMap<>();
			for (String pitanje : pitanjaId) {
				List<String> odg = new ArrayList<>();
				for (String odgovor : odgovoriId) {
					if (odgovor.split("-")[0].equals(pitanje)) {
						odg.add(form.getFirst(odgovor));
					}
				}
				fPitanja.put(pitanje, odg);
				forma.put(pitanje, form.getFirst(pitanje));
			}
			if (!fPitanja.isEmpty()) {
				forma.put("pitanja", fPitanja);
			}
			req.setAttribute("forma", forma);
			req.setAttribute("greska", greska);
			req.setAttribute("tab", "nova-anketa");

			return prikaziAnkete(req);
		}
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String prikaziAnketeKaoJSON(@Context HttpServletRequest req) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		JsonObject ankete = new JsonObject();
		Object obj = req.getSession().getAttribute("korisnik");
		if (obj != null) {
			ankete.add("privatne", gson.toJsonTree(DAOAnketa.getDAO().dohvatiPrivatneAnkete((Korisnik) obj)));
		}
		ankete.add("javne", gson.toJsonTree(DAOAnketa.getDAO().dohvatiJavneAnkete()));
		return gson.toJson(ankete);
	}

	@GET
	@Path("/{id-naziv}")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNazivAnketa) {
		if (idNazivAnketa == null || idNazivAnketa.length() == 0) {
			return Response.ok(new Viewable("/anketa")).build();
		}
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0]));
		req.setAttribute("anketa", anketa);
		return Response.ok(new Viewable("/anketa")).build();
	}

	@GET
	@Path("/{id-naziv}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String prikaziAnketuKaoJSON(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0]));
		return gson.toJson(anketa);
	}

}
