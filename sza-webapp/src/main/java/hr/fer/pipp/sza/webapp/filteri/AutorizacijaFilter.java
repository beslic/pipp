package hr.fer.pipp.sza.webapp.filteri;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.modeli.Korisnik;

@Provider
@PreMatching
public class AutorizacijaFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String url = "/" + requestContext.getUriInfo().getPath();
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute("korisnik");

		if (url.equals("/")) {
			return;
		}

		if (korisnik == null) {

			if (url.startsWith("/korisnici")) {
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else if (url.startsWith("/anketari")) {
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else if (url.startsWith("/admin")) {
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else if (url.startsWith("/narucitelji")) {
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else {
				requestContext.abortWith(Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build());
			}

			if (url.startsWith("/ankete")) {
				// TODO filtrirati samo javne ankete
			}

		}

		else {
			if (url.equals("/prijava/")) {
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else if (url.equals("/registracija/")) {
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else if (korisnik.getRazinaPrava() != 3 && url.startsWith("/admin")) { //ako nije admin
				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
			} else {
				requestContext.abortWith(Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build());
			}
		}

	}

}
