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

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;

@Path("/ankete/{idanketa}")
public class AnketaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketu(@Context HttpServletRequest req, @PathParam("idanketa") String idAnketa) {
		Anketa anketa = DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idAnketa));
		if (anketa != null && req.getSession().getAttribute("korisnik") != null) { // ako je korisnik logiran, nije bitno je li anketa privatna
			req.setAttribute("anketa", anketa);
		} else if (anketa != null && !anketa.isJePrivatna()) { // ako korisnik nije logiran, onda anketa mora biti javna da bi ju vidio
			req.setAttribute("anketa", anketa);
		}
		return Response.ok(new Viewable("/anketa")).build();
	}
}
