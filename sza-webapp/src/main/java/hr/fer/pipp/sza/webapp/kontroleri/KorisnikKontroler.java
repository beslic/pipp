package hr.fer.pipp.sza.webapp.kontroleri;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

@Path("/korisnici/{korisnickoime}")
public class KorisnikKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziKorisnika(@Context HttpServletRequest req,
			@PathParam("korisnickoime") String korisnickoIme) {
		Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme);
		req.setAttribute("trazenikorisnik", korisnik);
		return Response.ok(new Viewable("/korisnik")).build();

	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/postavke")
	public Response prikaziPostavkeKorisnika(@Context HttpServletRequest req,
			@PathParam("korisnickoime") String korisnickoIme) {
		Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme);
		req.setAttribute("postavke", korisnik);
		return Response.ok(new Viewable("/postavke")).build();
	}

}
