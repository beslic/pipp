package hr.fer.pipp.sza.webapp.filteri;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Provider
@PreMatching
public class AutorizacijaFilter implements ContainerRequestFilter {

	@Context
	private UriInfo uri;

	@Context
	private HttpServletRequest req;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		String url = "/" + uri.getPath();
		
		if (url.equals("/")) {
			return;
		}

		if (korisnik == null) {
			if (url.matches(Util.PRAVA_ANONIMNOG_KORISNIKA)) {
				if (url.matches("/ankete/[0-9]+-[A-Za-z0-9]+/")) {
					Util.provjeraPrivatnostiAnkete(req, uri);
				}
				return;
			} else if (url.matches(Util.PRAVA_REGISTRIRANOG_KORISNIKA)) {
				Util.r403();
			} else {
				Util.r404();
			}
		} else {
			if (korisnik.getRazinaPrava() == 1) { // ako nije admin
				if (url.matches(Util.PRAVA_REGISTRIRANOG_KORISNIKA)) {
					if (url.matches("/ankete/[0-9]+-[A-Za-z0-9]+/")) {
						Util.provjeraPrivatnostiAnkete(req, uri);
					}
					return;
				} else if (url.matches(Util.PRAVA_ANONIMNOG_KORISNIKA)) {
					Util.r403();
				} else {
					Util.r404();
				}
			} else { // ako je admin
				// TODO prava admina
			}
		}

	}
}
