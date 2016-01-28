package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import hr.fer.pipp.sza.webapp.utils.PasswordHash;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/registracija")
public class RegistracijaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziRegistracija(@Context HttpServletRequest req) throws ServletException, IOException {
		req.setAttribute("url", 2);
		return Response.ok(new Viewable("/registracija")).build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	public Response noviKorisnik(@Context HttpServletRequest request, @Context UriInfo uri,
			@FormParam("korisnickoime") String korisnickoIme, @FormParam("ime") String ime,
			@FormParam("prezime") String prezime, @FormParam("lozinka") String lozinka,
			@FormParam("lozinkapotvrda") String lozinkaPotvrda, @FormParam("register") String button,
			@FormParam("email") String email, @FormParam("prava") String razinaPrava) {

		Map<String, String> greska = Util.provjeriRegistracijskuFormu(korisnickoIme, ime, prezime, lozinka,
				lozinkaPotvrda, email, razinaPrava);

		if ("register".equals(button)) {

			if (greska.isEmpty()) {
				Korisnik korisnik = new Korisnik();
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik.setIme(ime);
				korisnik.setPrezime(prezime);
				korisnik.setEmail(email);
				korisnik.setRazinaPrava(("1".equals(razinaPrava)) ? 1 : 2);
				korisnik.setTrazenaRazinaPrava(korisnik.getRazinaPrava());
				korisnik.setAktivan("1".equals(razinaPrava) ? false : true);
				// ako je narucitelj, admin ga treba aktivirati
				try {
					korisnik.setLozinka(PasswordHash.createHash(lozinka));
				} catch (NoSuchAlgorithmException | InvalidKeySpecException ignorable) {
				}

				DAOKorisnik.getDAO().spremiNovogKorisnika(korisnik);

				// ako je korisnik narucitelj, ne stavljaj ga u session
				if (korisnik.getRazinaPrava() != 1) {
					request.getSession().setAttribute("korisnik", korisnik);
				}
				return Response.seeOther(URI.create(uri.getBaseUri().toString())).build();
			} else {
				Map<String, String> forma = new HashMap<>();
				forma.put("korisnickoime", korisnickoIme);
				forma.put("ime", ime);
				forma.put("prezime", prezime);
				forma.put("korisnickoime", korisnickoIme);
				forma.put("email", email);
				forma.put("prava", razinaPrava);
				forma.put("lozinka", lozinka);
				forma.put("lozinkapotvrda", lozinkaPotvrda);
				request.setAttribute("forma", forma);
				request.setAttribute("greska", greska);
				return Response.ok(new Viewable("/registracija")).build();
			}

		} else {
			System.out.println("");
			return Response.seeOther(uri.getBaseUri()).build();

		}
	}

}
