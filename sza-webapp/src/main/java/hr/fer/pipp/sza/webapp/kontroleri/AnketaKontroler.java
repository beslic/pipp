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

@Path("/ankete/{idnazivanketa}")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("idnazivanketa") String nazivAnketa) {
		req.setAttribute("anketa", nazivAnketa);
		return Response.ok(new Viewable("/anketa")).build();
	}
}
