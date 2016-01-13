package hr.fer.pipp.sza.webapp.kontroleri;

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
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;


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
	public Response prikaziPostavkeKorisnika(@Context HttpServletRequest req,
			@PathParam("korisnickoime") String korisnickoIme) {
		return Response.ok(new Viewable("/postavkeKorisnika")).build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response odjaviKorisnika(@Context HttpServletRequest req, @Context UriInfo uri, @FormParam("button") String button) {
		
		if ("signout".equals(button)) {
			req.getSession().invalidate();
			return Response.seeOther(uri.getBaseUri()).build();
		}
		return prikaziKorisnika(req, uri);
	}

}
