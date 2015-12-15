package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
public class IndexKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziIndex(@Context HttpServletRequest req) throws ServletException, IOException {
		List<String> Lista = new ArrayList<>();
		Lista.add("marko");
		Lista.add("adwdad");
		req.setAttribute("mojaLista", Lista);
		req.setAttribute("ime", "stringMoj");
		return Response.ok(new Viewable("/index")).build();
	}

	@GET
	@Path("ankete")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnkete(@Context HttpServletRequest req) throws ServletException, IOException {
		// TODO
		// Dodati popis anketa iz baze
		return Response.ok(new Viewable("/ankete")).build();
	}

	@GET
	@Path("anketari")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziAnketare(@Context HttpServletRequest req) throws ServletException, IOException {
		// TODO
		// Dodati popis anketara iz baze
		return Response.ok(new Viewable("/anketari")).build();
	}

	@GET
	@Path("korisnici")
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziKorisnike(@Context HttpServletRequest req) throws ServletException, IOException {
		// TODO
		// Dodati popis narucitelja iz baze
		return Response.ok(new Viewable("/korisnici")).build();
	}

}
