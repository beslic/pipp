package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

@Path("/prijava")
public class PrijavaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziPrijava(@Context HttpServletRequest req) throws ServletException, IOException {
		req.setAttribute("url", 1);
		return Response.ok(new Viewable("/prijava")).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public Response noviKorisnik(@Context HttpServletRequest request, @Context UriInfo uri,
			@FormParam("korisnickoime") String korisnickoIme,
			@FormParam("lozinka") String lozinka)
			 {

		Map<String, String> greska = Util.provjeriFormuPrijavljivanja(korisnickoIme, lozinka);

		if (greska.isEmpty()) {
			Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme);
			
			request.getSession().setAttribute("korisnik", korisnik);
			return Response.seeOther(URI.create(uri.getBaseUri().toString())).build();
		} else {
			Map<String, String> forma = new HashMap<>();
			forma.put("korisnickoime", korisnickoIme);
			request.setAttribute("forma", forma);
			request.setAttribute("greska", greska);
			return Response.ok(new Viewable("/prijava")).build();
		}
	}

}
