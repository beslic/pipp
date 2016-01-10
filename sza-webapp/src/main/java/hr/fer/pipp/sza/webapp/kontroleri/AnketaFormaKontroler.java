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
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/anketaforma")
public class AnketaFormaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketaForma() throws ServletException, IOException {
		return Response.ok(new Viewable("/anketforma")).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public Response novaAnketa(@Context HttpServletRequest request, @Context UriInfo uri,
			@FormParam("imeAnketa") String nazivAnketa,
			@FormParam("opisAnketa") String opisAnketa)
			 {
		String aktivnaOd = new String("");
		String aktivnaDo = new String("");
		String brojPitanja = new String("");

		Map<String, String> greska = Util.provjeriFormuAnkete(nazivAnketa, opisAnketa, aktivnaOd, aktivnaDo, brojPitanja);

		if (greska.isEmpty()) {
			
			return Response.seeOther(URI.create(uri.getBaseUri().toString())).build();
		}
		 else {
			
			return Response.ok(new Viewable("/anketaforma")).build();
		}
}
}
