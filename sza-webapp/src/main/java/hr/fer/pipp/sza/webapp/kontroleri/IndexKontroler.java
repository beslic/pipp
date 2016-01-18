package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/")
public class IndexKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziIndex(@Context HttpServletRequest req) throws ServletException, IOException {
		
		return Response.ok(new Viewable("/index")).build();
	}

	@GET
	@Path("anketari")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketare(@Context HttpServletRequest req) throws ServletException, IOException {
		// TODO
		// Dodati popis anketara iz baze
		Util.setAktivno(req, "aktivAnk");
		return Response.ok(new Viewable("/anketari")).build();
	}

	@GET
	@Path("korisnici")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziKorisnike(@Context HttpServletRequest req) throws ServletException, IOException {
		// TODO
		// Dodati popis narucitelja iz baze
		List<Korisnik> listaKorisnika = DAOKorisnik.getDAO().dohvatiSveKorisnike();
		
		req.setAttribute("korisniciNeakt", listaKorisnika.stream().filter(kr -> kr.isAktivan() == false).collect(Collectors.toList()));
		req.setAttribute("korisniciAkt", listaKorisnika.stream().filter(kr -> kr.isAktivan() == true).collect(Collectors.toList()));
		Util.setAktivno(req, "aktivKor");
		return Response.ok(new Viewable("/korisnici")).build();
	}

}
