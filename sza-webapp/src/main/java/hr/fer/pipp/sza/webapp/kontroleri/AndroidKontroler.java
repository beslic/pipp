package hr.fer.pipp.sza.webapp.kontroleri;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import hr.fer.pipp.sza.webapp.dao.DAOIspn;
import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.utils.Json;
import hr.fer.pipp.sza.webapp.utils.Util;

@Path("/android")
public class AndroidKontroler {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String provjeriKorisnika(String json) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		if (json == null || json.length() < 2) {
			return gson.toJson(Json.setStatus("failed"));
		}
		Map<String, String> greska = Util.provjeriPrijavu(Json.prijava(json, "ime"), Json.prijava(json, "lozinka"));
		JsonObject jsonObj;
		if (greska.isEmpty()) {
			jsonObj = Json.setStatus("success");
			jsonObj.add("korisnik",
					new JsonParser()
							.parse(gson.toJson(DAOKorisnik.getDAO().dohvatiKorisnika(Json.prijava(json, "ime"))))
							.getAsJsonObject());
		} else {
			jsonObj = Json.setStatus("failed");
			if (greska.containsKey("ime")) {
				jsonObj.addProperty("errormessage", greska.get("ime"));
			} else if (greska.containsKey("lozinka")) {
				jsonObj.addProperty("errormessage", greska.get("lozinka"));
			}
		}
		return gson.toJson(jsonObj);
	}

	@POST
	@Path("/result")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static String spremiRezultate(String json) {
		JsonObject obj = null;
		try {
			JsonArray root = new JsonParser().parse(json).getAsJsonArray();
			int vel = DAOIspn.getDAO().spremiIspunjavanja(Json.jsonToIspn(root)).size();
			obj = Json.setStatus("success");
			obj.addProperty("spremljeno", vel);
		} catch (JsonParseException ex) {
			obj = Json.setStatus("failed");
			obj.addProperty("error", ex.getMessage());
		}
		return new Gson().toJson(obj);
	}

}
