package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.server.mvc.Viewable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.dao.DAOIspn;
import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Ispunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.modeli.Odgovor;
import hr.fer.pipp.sza.webapp.modeli.Pitanje;
import hr.fer.pipp.sza.webapp.utils.ChartData;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/ankete/")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnkete(@Context HttpServletRequest req) {
		Util.setAktivno(req, "aktivAnkete");
		return prikaziAnkete(req, DAOAnketa.getDAO().dohvatiJavneAnkete(), "Javne ankete",
				"Nema dostupnih javnih anketa", "");
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziAnketeJSON(@Context HttpServletRequest req) {
		return prikaziAnketeJSON(DAOAnketa.getDAO().dohvatiJavneAnkete());
	}

	@GET
	@Path("/{id-naziv}")
	@Produces(MediaType.TEXT_HTML)
	public static Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv) {
		if (idNaziv == null || idNaziv.length() == 0) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		Util.setAktivno(req, "aktivAnkete");
		// TODO privremeno
		return ispunjavanjeAnkete(req, DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0])));
	}

	@POST
	@Path("/{id-naziv}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static Response spremiIspunjavanje(@Context HttpServletRequest req, MultivaluedMap<String, String> form,
			@PathParam("id-naziv") String idNaziv) {
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0]));
		Map<Pitanje, Odgovor> odgovori = new LinkedHashMap<>();
		anketa.getPitanja().forEach(p -> odgovori.put(p,
				p.getOdgovor().stream().filter(o -> Long
						.compare(Long.parseLong(form.getFirst(Long.toString(p.getIdPitanje()))), o.getIdOdgovor()) == 0)
				.findFirst().get()));
		DAOIspn.getDAO().spremiIspunjavanje(new Ispunjavanje(null, anketa, null, odgovori));
		return Response.seeOther(UriBuilder.fromPath("ankete/" + idNaziv + "/rezultati/").build()).build();
	}

	@GET
	@Path("/{id-naziv}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response prikaziAnketuKaoJSON(@Context HttpServletRequest req,
			@PathParam("id-naziv") String idNaziv) {
		return prikaziAnketuJSON(DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0])));
	}

	@GET
	@Path("/{id-naziv}/rezultati")
	@Produces(MediaType.TEXT_HTML)
	public static Response prikaziRezultateAnkete(@Context HttpServletRequest req,
			@PathParam("id-naziv") String idNaziv) {
		Util.setAktivno(req, "aktivAnkete");
		Anketa a = DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0]));
		req.setAttribute("anketa", a);
		req.setAttribute("data", ChartData.getData(a));
		return Response.ok(new Viewable("/rezultatiAnkete")).build();
	}

	@GET
	@Path("/{id-naziv}/dozvole")
	@Produces(MediaType.TEXT_HTML)
	public static Response prikaziDozvoleAnkete(@Context HttpServletRequest req,
			@PathParam("id-naziv") String idNaziv) {
		Util.setAktivno(req, "aktivAnkete");

		Anketa a = DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0]));
		req.setAttribute("anketa", a);

		List<Korisnik> listaAnketara = DAOKorisnik.getDAO().dohvatiSveKorisnike();
		req.setAttribute("anketari", listaAnketara.stream()
				.filter(kr -> kr.isAktivan() == true && kr.getRazinaPrava() == 1).collect(Collectors.toList()));

		// iz baze dohvati trenutne dozvole

		return Response.ok(new Viewable("/dozvoleAnkete")).build();
	}

	@POST
	@Path("/{id-naziv}/dozvole")
	@Produces(MediaType.TEXT_HTML)
	public Response izmjeniDozvoleAnkete(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv,
			MultivaluedMap<String, String> checkboxes) throws ServletException, IOException {
		// Util.azurirajDozvoleAnkete(checkboxes);
		return prikaziDozvoleAnkete(req, idNaziv);
	}

	public static Response prikaziAnkete(HttpServletRequest req, List<Anketa> ankete, String naslov, String prazno,
			String url) {
		req.setAttribute("naslov", naslov);
		req.setAttribute("prazno", prazno);
		req.setAttribute("url", url);
		req.setAttribute("ankete", ankete);
		return Response.ok(new Viewable("/prikazAnketa")).build();
	}

	public static Response prikaziFormuAnkete(HttpServletRequest req, String naslov) {
		List<Korisnik> anketari = DAOKorisnik.getDAO().dohvatiSveAnketare();
		if (!anketari.isEmpty()) {
			req.setAttribute("anketari", anketari);
		}
		req.setAttribute("naslov", naslov);
		return Response.ok(new Viewable("/anketaForma")).build();
	}

	public static Response izmijeniAnketu(HttpServletRequest req, Anketa anketa) {
		Util.setAktivno(req, "aktivMoje");
		req.setAttribute("akcija", "izmijeni");
		req.setAttribute("anketa", anketa);
		req.setAttribute("aktivnaDoForma", Util.formatDatum(anketa.getAktivnaDo()));
		req.setAttribute("aktivnaOdForma", Util.formatDatum(anketa.getAktivnaOd()));
		return prikaziFormuAnkete(req, "Izmijeni anketu " + anketa.getNazivAnketa());
	}

	public static Response prikaziAnketeJSON(List<Anketa> ankete) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return Response.ok(gson.toJson(ankete)).build();
	}

	public static Response ispunjavanjeAnkete(HttpServletRequest req, Anketa anketa) {
		req.setAttribute("anketa", anketa);
		return Response.ok(new Viewable("/ispunjavanjeAnkete")).build();
	}

	public static Response prikaziAnketuJSON(Anketa anketa) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return Response.ok(gson.toJson(anketa)).build();
	}

	public static Response spremiAnketu(@Context HttpServletRequest req, MultivaluedMap<String, String> form,
			Anketa anketa, String url) {
		Map<String, String> greska = new HashMap<>();
		Anketa nova = Util.provjeriFormuAnkete(form, greska);
		if (greska.isEmpty()) {
			if ("nova".equals(form.getFirst("spremi"))) {
				nova.setVlasnik((Korisnik) req.getSession().getAttribute("korisnik"));
				nova.getAnketari().add(nova.getVlasnik());
				nova.getVlasnik().getAnkete().add(nova);
				DAOAnketa.getDAO().spremiAnketu(nova);
			} else if ("izmijeni".equals(form.getFirst("spremi"))) {
				nova.setVlasnik(anketa.getVlasnik());
				nova.setIdAnketa(anketa.getIdAnketa());
				DAOAnketa.getDAO().spremiIzmjeneAnkete(nova);
			}
			return Response.seeOther(UriBuilder.fromUri(url).build()).build();
		} else {
			req.setAttribute("anketa", anketa);
			req.setAttribute("forma", form);
			req.setAttribute("greska", greska);
			return KorisnikKontroler.prikaziFormuZaNovuAnketu(req);
		}
	}

	public static Response spremiRezultate() {
		return null;
	}

}
