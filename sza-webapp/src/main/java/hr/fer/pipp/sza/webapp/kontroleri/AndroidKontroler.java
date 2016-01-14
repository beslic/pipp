package hr.fer.pipp.sza.webapp.kontroleri;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/android")
public class AndroidKontroler {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String provjeriKorisnika(String json) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		System.out.println("Android: " + json);

		if (json == null || json.length() < 2) {
			return "{\"status\":\"failed\"}";
		}

		JsonElement je = new JsonParser().parse(json);
		JsonObject jo = je.getAsJsonObject();

		Map<String, String> greska = null;
		try {
			greska = Util.provjeriFormuPrijavljivanjaAnketara(jo.get("ime").getAsString(),
					jo.get("lozinka").getAsString());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		JsonObject jsonObj = new JsonObject();

		if (greska.isEmpty()) {
			jsonObj.addProperty("status", "success");
			// dohvati korisnika iz baze
			Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(jo.get("ime").getAsString());
			// pretvori ga u JSON objekt
			JsonObject innerJson = new JsonParser().parse(gson.toJson(korisnik)).getAsJsonObject();
			// spremi ga kako property vanjskog JSON-a
			jsonObj.add("korisnik", innerJson);
		} else {
			jsonObj.addProperty("status", "failed");
			if (greska.containsKey("ime")) {
				jsonObj.addProperty("errormessage", greska.get("ime"));
			} else if (greska.containsKey("lozinka")) {
				jsonObj.addProperty("errormessage", greska.get("lozinka"));
			}
		}

		return jsonObj.toString();
	}

}
