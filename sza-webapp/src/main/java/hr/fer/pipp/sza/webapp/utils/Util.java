package hr.fer.pipp.sza.webapp.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private static boolean validirajEmail(String email) {

		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static Map<String, String> provjeriRegistracijskuFormu(String korisnickoIme, String ime, String prezime,
			String lozinka, String lozinkaPotvrda, String email) {

		Map<String, String> greska = new HashMap<>();

		// TODO
		// PROVJERITI MAIL I USERNAME U BAZI

		if (ime == null || ime.length() == 0) {
			greska.put("ime", "Ime je prazno");
		}
		if (prezime == null || prezime.length() == 0) {
			greska.put("prezime", "Prezime je prazno");
		}
		if (email == null || !validirajEmail(email)) {
			greska.put("email", "Email nije valjan");
		}
		if (lozinka == null || lozinka.length() < 8) {
			greska.put("lozinka", "Lozinka mora imati barem 8 znakova");
		} else if (!lozinka.equals(lozinkaPotvrda)) {
			greska.put("lozinkapotvrda", "Lozinke se ne podudaraju");
		}

		return greska;
	}

}
