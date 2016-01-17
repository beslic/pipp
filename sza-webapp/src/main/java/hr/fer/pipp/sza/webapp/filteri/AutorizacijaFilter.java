package hr.fer.pipp.sza.webapp.filteri;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
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
				if (url.matches("/ankete/[0-9]+-[A-Za-z0-9\\-]+/")) {
					Util.provjeraPrivatnostiAnkete(requestContext, req, uri);
				}
				return;
			} else if (url.matches(Util.PRAVA_REGISTRIRANOG_KORISNIKA)) {
				requestContext.abortWith(Util.r403());
			} else {
				requestContext.abortWith(Util.r404());
			}
		} else {
			if (korisnik.getRazinaPrava() == 1) { // ako nije admin
				if (url.matches(Util.PRAVA_REGISTRIRANOG_KORISNIKA)) {

					if (url.matches("/korisnici/[A-Za-z0-9]+/ankete/[0-9]+-[A-Za-z0-9\\-]+/(izmijeni/){0,1}")) {
						if (url.endsWith("/izmijeni/")) {
							String idNazivAnketa = uri.getPathSegments().get(uri.getPathSegments().size() - 3)
									.toString();
							Anketa a = DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0]));
							if (a != null) {
								if (a.isJePrivatna()) {
									Korisnik k = (Korisnik) req.getSession().getAttribute("korisnik");
									if (k == null) {
										requestContext.abortWith(Util.r404());
									}
									if (!k.equals(a.getVlasnik())) {
										requestContext.abortWith(Util.r403());
									}
								}
								req.setAttribute("anketa", a);
								return;
							} else {
								requestContext.abortWith(Util.r404());
							}
						} else {
							Util.provjeraPrivatnostiAnkete(requestContext, req, uri);
						}

					} else if (url.matches("/ankete/[0-9]+-[A-Za-z0-9]+/")) {
						Util.provjeraPrivatnostiAnkete(requestContext, req, uri);
					}
					return;
				} else if (url.matches(Util.PRAVA_ANONIMNOG_KORISNIKA)) {
					requestContext.abortWith(Util.r403());
				} else {
					requestContext.abortWith(Util.r404());
				}
			} else { // ako je admin
				// TODO prava admina
			}
		}

	}
}
