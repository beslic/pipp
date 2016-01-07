package hr.fer.pipp.sza.webapp.filteri;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AutorizacijaFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

//		String url = "/" + requestContext.getUriInfo().getPath();
//		Korisnik korisnik = (Korisnik) request.getSession().getAttribute("korisnik");
//
//		if (url.equals("/")) {
//			return;
//		}
//
//		if (korisnik == null) {
//
//			if (url.startsWith("/korisnici")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.startsWith("/anketari")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.startsWith("/admin")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.startsWith("/narucitelji")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.equals("/odjava/")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if(url.equals("/android/")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.startsWith("/ankete")) {
//				// TODO filtrirati samo javne ankete
//			} else if (url.equals("/prijava/") || url.equals("/registracija/")) {
//				return;
//			} else {
//				requestContext.abortWith(Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build());
//			}
//		}
//
//		else {
//			if (url.equals("/prijava/")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.equals("/registracija/")) {
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (korisnik.getRazinaPrava() != 3 && url.startsWith("/admin")) { //ako nije admin
//				requestContext.abortWith(Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build());
//			} else if (url.startsWith("/korisnici") || url.startsWith("/anketari") || url.startsWith("/ankete")
//					|| url.equals("/odjava/") || url.equals("/android/")) {
//				return;
//			} else {
//				requestContext.abortWith(Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build());
//			}
//		}

	}

}
