package hr.fer.pipp.sza.webapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.GPSPozicija;
import hr.fer.pipp.sza.webapp.modeli.Ispunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Odgovor;
import hr.fer.pipp.sza.webapp.modeli.Pitanje;

public class Json {

	public static List<Ispunjavanje> jsonToIspn(JsonArray root) {
		List<Ispunjavanje> ispn = new ArrayList<>();
		root.forEach(je -> ispn.add(dohvatiIspunjavanje(je.getAsJsonObject())));
		return ispn;
	}

	private static Ispunjavanje dohvatiIspunjavanje(JsonObject jo) {
		Ispunjavanje ispn = new Ispunjavanje();
		ispn.setAnketa(DAOAnketa.getDAO().dohvatiAnketu(jo.get("anketaId").getAsLong()));
		ispn.setAnketar(DAOKorisnik.getDAO().dohvatiKorisnika(jo.get("korisnickoIme").getAsString()));
		try {
			ispn.setVrijeme(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jo.get("dateTime").getAsString()));
		} catch (ParseException ignorable) {
		}
		ispn.setPozicija((jo.get("poznataLokacija").getAsBoolean())
				? new GPSPozicija(jo.get("latitude").getAsDouble(), jo.get("longitude").getAsDouble()) : null);
		// TODO maknuti ovo ispod
		ispn.setPozicija(new GPSPozicija(Math.random(), Math.random()));
		ispn.setOdgovori(dohvatiOdgovore(jo.get("odabraniOdgovori").getAsJsonArray(), ispn.getAnketa()));
		return ispn;
	}

	private static Map<Pitanje, Odgovor> dohvatiOdgovore(JsonArray odbOdg, Anketa a) {
		Map<Pitanje, Odgovor> odgovori = new HashMap<>();

		odbOdg.forEach(je -> {
			Pitanje pit = a.getPitanja().stream()
					.filter(p -> Long.compare(je.getAsJsonObject().get("pitanjeId").getAsLong(), p.getIdPitanje()) == 0)
					.findFirst().get();
			Odgovor odg = pit.getOdgovor().stream()
					.filter(o -> Long.compare(je.getAsJsonObject().get("odgovorId").getAsLong(), o.getIdOdgovor()) == 0)
					.findFirst().get();
			odgovori.put(pit, odg);
		});
		return odgovori;
	}

	public static JsonObject setStatus(String status) {
		JsonObject obj = new JsonObject();
		obj.addProperty("status", status);
		return obj;
	}

	public static String prijava(String json, String atribut) {
		return new JsonParser().parse(json).getAsJsonObject().get(atribut).getAsString();
	}

}
