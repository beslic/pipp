package hr.fer.pipp.sza.webapp.filteri;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import hr.fer.pipp.sza.webapp.modeli.Korisnik;

@Provider
@PreMatching
public class AutorizacijaFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String url = requestContext.getUriInfo().getAbsolutePath().toString();
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute("korisnik");

		if (korisnik == null) {

			if (url.endsWith("korisnici/")) {
				try {
					response.addHeader("Refresh", "3; ../prijava/");
					request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/403.jsp").forward(request, response);
				} catch (ServletException e) {
				}
			}
			
			if (url.matches("^.*/korisnici/[A-z0-9]+/.*")) {
				try {
					response.addHeader("Refresh", "3; ../../prijava/");
					request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/403.jsp").forward(request, response);
				} catch (ServletException e) {
				}
			}

			if (url.endsWith("ankete/")) {
				//TODO filtrirati samo javne ankete
			}

		}
	}

}
