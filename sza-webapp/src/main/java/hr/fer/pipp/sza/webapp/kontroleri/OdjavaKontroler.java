package hr.fer.pipp.sza.webapp.kontroleri;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/odjava")
public class OdjavaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response odjaviKorisnika(@Context HttpServletRequest req) {
		req.getSession().removeAttribute("korisnik");
		return Response.ok(new Viewable("/prijava")).build();
	}

}
