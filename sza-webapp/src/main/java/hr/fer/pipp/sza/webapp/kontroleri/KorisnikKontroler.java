package hr.fer.pipp.sza.webapp.kontroleri;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/korisnici/{korisnickoime}")
public class KorisnikKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziKorisnika(@Context HttpServletRequest req, @Context UriInfo uri) {
		return Response.ok(new Viewable("/korisnik")).build();
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

		if ("postavke".equals(button)) {
			Map<String, String> greska = Util.provjeriFormuPostavkiKorisnika(ime, prezime, email);

			if (greska.isEmpty()) {
				Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
				
				korisnik.setIme(ime);
				korisnik.setPrezime(prezime);
				korisnik.setEmail(email);

				DAOKorisnik.getDAO().spremiIzmjeneKorisnika(korisnik);
				return prikaziKorisnika(req, uri);
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
				return prikaziKorisnika(req, uri);
			} else {
				req.setAttribute("greska", greska);
				return prikaziPostavkeKorisnika(req);
			}
		} else {
			return prikaziPostavkeKorisnika(req);
		}
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
		return prikaziKorisnika(req, uri);
	}

}
