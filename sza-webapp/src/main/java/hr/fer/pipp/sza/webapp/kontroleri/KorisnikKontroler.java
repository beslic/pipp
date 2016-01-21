package hr.fer.pipp.sza.webapp.kontroleri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.PasswordHash;
import hr.fer.pipp.sza.webapp.utils.Util;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@Path("/korisnici/{korisnickoime}")
public class KorisnikKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziKorisnika(@Context HttpServletRequest req, @PathParam("korisnickoime") String ime) {
		if (DAOKorisnik.getDAO().dohvatiKorisnika(ime) == null) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		Util.setAktivno(req, "aktivProfil");
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
		Util.setAktivno(req, "aktivProfil");
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

		if ("postavke".equals(button)) {
			Map<String, String> greska = Util.provjeriFormuPostavkiKorisnika(ime, prezime, email);

			if (greska.isEmpty()) {
				Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");

				korisnik.setIme(ime);
				korisnik.setPrezime(prezime);
				korisnik.setEmail(email);

				DAOKorisnik.getDAO().spremiIzmjeneKorisnika(korisnik);
				return Response.seeOther(URI.create("/sza-webapp/korisnici/" + korisnik.getKorisnickoIme() + "/"))
						.build();

			} else {
				req.setAttribute("greska", greska);
				return prikaziPostavkeKorisnika(req);
			}

		} else if ("postavkelozinka".equals(button)) {
			Map<String, String> greska = Util.provjeriFormuPromjeneLozinke(staraLozinka, novaLozinka,
					novaLozinkaPotvrda);

			if (greska.isEmpty()) {
				Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
				try {
					korisnik.setLozinka(PasswordHash.createHash(novaLozinkaPotvrda));
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// ignore
				}

				DAOKorisnik.getDAO().spremiIzmjeneKorisnika(korisnik);
				return Response.seeOther(URI.create("/sza-webapp/korisnici/" + korisnik.getKorisnickoIme() + "/"))
						.build();

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
	public static Response prikaziAnketeKorisnika(@Context HttpServletRequest req,
			@PathParam("korisnickoime") String name) {
		Util.setAktivno(req, "aktivMoje");
		return AnketaKontroler.prikaziAnkete(req, ((Korisnik) req.getSession().getAttribute("korisnik")).getAnketa(),
				"Moje ankete", "Nemate napravljenih anketa", "korisnici/" + name + "/");
	}

	@GET
	@Path("ankete/json")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response prikaziAnketeJSON(@Context HttpServletRequest req) {
		return AnketaKontroler.prikaziAnketeJSON(((Korisnik) req.getSession().getAttribute("korisnik")).getAnketa());
	}

	@GET
	@Path("ankete/nova")
	@Produces(MediaType.TEXT_HTML)
	public static Response prikaziFormuZaNovuAnketu(@Context HttpServletRequest req) {
		Util.setAktivno(req, "aktivNova");
		req.setAttribute("akcija", "nova");
		return AnketaKontroler.prikaziFormuAnkete(req, "Nova anketa");
	}

	@POST
	@Path("ankete/nova")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static Response dodajAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv,
			MultivaluedMap<String, String> form) {
		String url = "/sza-webapp/korisnici/"
				+ ((Korisnik) req.getSession().getAttribute("korisnik")).getKorisnickoIme() + "/ankete/";
		return AnketaKontroler.spremiAnketu(req, form, null, url);
	}

	@GET
	@Path("ankete/{id-naziv}")
	@Produces(MediaType.TEXT_HTML)
	public static Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNazivAnketa) {
		if (idNazivAnketa == null || idNazivAnketa.length() == 0) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		Util.setAktivno(req, "aktivMoje");
		return AnketaKontroler.ispunjavanjeAnkete(req,
				DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0])));
	}

	@GET
	@Path("ankete/{id-naziv}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response prikaziAnketuKaoJSON(@Context HttpServletRequest req,
			@PathParam("id-naziv") String idNaziv) {
		if (idNaziv == null || idNaziv.length() == 0) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		return AnketaKontroler
				.prikaziAnketuJSON(DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0])));
	}

	@GET
	@Path("ankete/{id-naziv}/izmijeni")
	@Produces(MediaType.TEXT_HTML)
	public static Response izmijeniAnketu(@Context HttpServletRequest req,
			@PathParam("id-naziv") String idNazivAnketa) {
		if (idNazivAnketa == null || idNazivAnketa.length() == 0) {
			return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
		}
		return AnketaKontroler.izmijeniAnketu(req,
				DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0])));
	}

	@POST
	@Path("ankete/{id-naziv}/izmijeni")
	@Produces(MediaType.TEXT_HTML)
	public static Response izmijeniAnketu(@Context HttpServletRequest req, @PathParam("id-naziv") String idNaziv,
			MultivaluedMap<String, String> form) {
		String url = "/sza-webapp/korisnici/"
				+ ((Korisnik) req.getSession().getAttribute("korisnik")).getKorisnickoIme() + "/ankete/" + idNaziv
				+ "/";
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Long.parseLong(idNaziv.split("-")[0]));
		return AnketaKontroler.spremiAnketu(req, form, anketa, url);
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
