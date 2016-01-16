package hr.fer.pipp.sza.webapp.kontroleri;

import java.util.Map;

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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/korisnici/{korisnickoime}")
public class KorisnikKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziKorisnika(@Context HttpServletRequest req, @PathParam("korisnickoime") String ime) {
		if (DAOKorisnik.getDAO().dohvatiKorisnika(ime) == null) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		return Response.ok(new Viewable("/korisnik")).build();
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String jsonKorisnik(@Context HttpServletRequest req) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return gson.toJson(req.getSession().getAttribute("korisnik"), Korisnik.class);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/postavke")
	public Response prikaziPostavkeKorisnika(@Context HttpServletRequest req) {
		return Response.ok(new Viewable("/postavkeKorisnika")).build();
	}

	@POST
	@Path("/postavke")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response promjeniPostavkeKorisnika(@Context HttpServletRequest req, @Context UriInfo uri,
			@FormParam("ime") String ime, @FormParam("prezime") String prezime, @FormParam("email") String email,
			@FormParam("buttonPostavke") String button, @FormParam("staralozinka") String staraLozinka,
			@FormParam("novalozinka") String novaLozinka, @FormParam("novalozinkapotvrda") String novaLozinkaPotvrda) {

		System.out.println(ime);
		System.out.println(prezime);
		System.out.println(email);
		System.out.println(button);
		System.out.println(staraLozinka);
		System.out.println(novaLozinka);
		System.out.println(novaLozinkaPotvrda);
		System.out.println();

		if ("postavke".equals(button)) {
			Map<String, String> greska = Util.provjeriFormuPostavkiKorisnika(ime, prezime, email);

			if (greska.isEmpty()) {
				Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");

				korisnik.setIme(ime);
				korisnik.setPrezime(prezime);
				korisnik.setEmail(email);

				DAOKorisnik.getDAO().spremiIzmjeneKorisnika(korisnik);
				return prikaziKorisnika(req, korisnik.getKorisnickoIme());
			} else {
				req.setAttribute("greska", greska);
				return prikaziPostavkeKorisnika(req);
			}

		} else if ("postavkelozinka".equals(button)) {
			Map<String, String> greska = Util.provjeriFormuPromjeneLozinke(staraLozinka, novaLozinka,
					novaLozinkaPotvrda);

			if (greska.isEmpty()) {
				Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
				korisnik.setLozinka(novaLozinkaPotvrda);

				DAOKorisnik.getDAO().spremiIzmjeneKorisnika(korisnik);
				return prikaziKorisnika(req, korisnik.getKorisnickoIme());
			} else {
				req.setAttribute("greska", greska);
				return prikaziPostavkeKorisnika(req);
			}
		} else {
			return prikaziPostavkeKorisnika(req);
		}
	}

	@GET
	@Path("/ankete")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketeKorisnika(@Context HttpServletRequest req, @PathParam("korisnickoime") String name) {
		return AnketaKontroler.prikaziAnkete(req, ((Korisnik) req.getSession().getAttribute("korisnik")).getAnketa(),
				"Moje ankete", "Niste napravili niti jednu anketu", "korisnici/" + name + "/");
	}

	@GET
	@Path("ankete/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziAnketeJSON(@Context HttpServletRequest req) {
		return AnketaKontroler.prikaziAnketeJSON(((Korisnik) req.getSession().getAttribute("korisnik")).getAnketa());
	}

	@GET
	@Path("ankete/{id-naziv}")
	@Produces(MediaType.TEXT_HTML)
	public static Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNazivAnketa) {
		if (idNazivAnketa == null || idNazivAnketa.length() == 0) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		return AnketaKontroler.ispunjavanjeAnkete(req,
				DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0])));
	}

	@GET
	@Path("ankete/{id-naziv}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikaziAnketuKaoJSON(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv) {
		return AnketaKontroler
				.prikaziAnketuJSON(DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0])));
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response odjaviKorisnika(@Context HttpServletRequest req, @Context UriInfo uri,
			@FormParam("button") String button) {
		if ("signout".equals(button)) {
			req.getSession().invalidate();
			return Response.seeOther(uri.getBaseUri()).build();
		}
		return prikaziKorisnika(req, ((Korisnik) req.getSession().getAttribute("korisnik")).getKorisnickoIme());
	}

}
