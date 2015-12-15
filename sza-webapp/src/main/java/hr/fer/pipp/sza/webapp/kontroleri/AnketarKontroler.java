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

@Path("/anketari/{korisnickoime}")
public class AnketarKontroler {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketara(@Context HttpServletRequest req,
			@PathParam("korisnickoime") String korisnickoIme) {
		req.setAttribute("korisnickoime", korisnickoIme);
		// TODO
		// Dohvatiti korisnika iz baze te ga poslati jsp-u
		return Response.ok(new Viewable("/korisnik")).build();
	}
}
