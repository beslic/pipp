package hr.fer.pipp.sza.webapp.kontroleri;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/registracija")
public class RegistracijaKontroler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response prikaziRegistracija() throws ServletException, IOException {
		return Response.ok(new Viewable("/registracija")).build();
	}

	@POST
	public Response noviKorisnik(@FormParam("korisnickoime") String korisnickoIme, @FormParam("ime") String ime,
			@FormParam("prezime") String prezime, @FormParam("lozinka") String lozinka,
			@FormParam("lozinkapotvrda") String lozinkaPotvrda, @FormParam("email") String email,
			@FormParam("razina") String razinaPrava) {

		Map<String, String> greska = Util.provjeriRegistracijskuFormu(korisnickoIme, ime, prezime, lozinka,
				lozinkaPotvrda, email);

		return null;
	}

}
