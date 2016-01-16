package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/ankete/")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnkete(@Context HttpServletRequest req) {
		return prikaziAnkete(req, DAOAnketa.getDAO().dohvatiJavneAnkete(), "Javne ankete",
				"Nema dostupnih javnih anketa", "");
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response dodajAnketu(@Context HttpServletRequest req, @Context UriInfo uri,
			MultivaluedMap<String, String> form) throws ParseException, ServletException, IOException {

		Anketa anketa = new Anketa();
		Map<String, Object> forma = new HashMap<>();
		Set<String> pitanjaId = new LinkedHashSet<>();
		Set<String> odgovoriId = new LinkedHashSet<>();
		Map<String, String> greska = Util.provjeriFormuAnkete(anketa, form, pitanjaId, odgovoriId, forma);

		if (greska.isEmpty()) {
			anketa.setVlasnik((Korisnik) req.getSession().getAttribute("korisnik"));
			DAOAnketa.getDAO().spremiAnketu(anketa);
			return Response.seeOther(UriBuilder.fromUri(uri.getRequestUri().toString()).build()).build();
		} else {
			req.setAttribute("forma", forma);
			req.setAttribute("greska", greska);
			return prikaziAnkete(req);
		}
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
	public static Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNazivAnketa) {
		if (idNazivAnketa == null || idNazivAnketa.length() == 0) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		return ispunjavanjeAnkete(req, DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0])));
	}

	@GET
	@Path("/{id-naziv}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziAnketuKaoJSON(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv) {
		return prikaziAnketuJSON(DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0])));
	}

	public static Response prikaziAnkete(@Context HttpServletRequest req, List<Anketa> ankete, String naslov,
			String nemaAnketa, String url) {
		req.setAttribute("naslov", naslov);
		req.setAttribute("nema-anketa", nemaAnketa);
		req.setAttribute("url", url);
		req.setAttribute("ankete", ankete);
		return Response.ok(new Viewable("/prikazAnketa")).build();
	}

	public static Response prikaziAnketeJSON(List<Anketa> ankete) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return Response.ok(gson.toJson(ankete)).build();
	}

	public static Response ispunjavanjeAnkete(@Context HttpServletRequest req, Anketa anketa) {
		req.setAttribute("anketa", anketa);
		return Response.ok(new Viewable("/ispunjavanjeAnkete")).build();
	}

	public static Response prikaziAnketuJSON(Anketa anketa) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return Response.ok(gson.toJson(anketa)).build();
	}

}
