package hr.fer.pipp.sza.webapp.kontroleri;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/android")
public class AndroidKontroler {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String provjeriKorisnika(@Context HttpServletRequest req) {
		Gson gson = new Gson();
		
//		Korisnik korisnik = gson.fromJson((String) req.getAttribute("json"), Korisnik.class);
//		Map<String, String> greska = Util.provjeriFormuPrijavljivanja(korisnik.getKorisnickoIme(),
//				korisnik.getLozinka());
//		Map<String, Object> podaci = new HashMap<>();
//
//		if (greska.isEmpty()) {
//			podaci.put("success", "OK");
//			podaci.put("korisnik", korisnik);
//		} else {
//			podaci.put("success", "failed");
//			if (greska.containsKey("korisnickoime")) {
//				podaci.put("errormessage", greska.get("korisnickoime"));
//			} else if (greska.containsKey("lozinka")) {
//				podaci.put("errormessage", greska.get("lozinka"));
//			}
//		}
//		req.setAttribute("json", gson.toJson(podaci));
		return gson.toString();

	}

}
