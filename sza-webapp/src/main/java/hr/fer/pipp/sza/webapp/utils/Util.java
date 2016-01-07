package hr.fer.pipp.sza.webapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public class Util {

	private static boolean validirajEmail(String email) {

		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static Map<String, String> provjeriRegistracijskuFormu(String korisnickoIme, String ime, String prezime,
			String lozinka, String lozinkaPotvrda, String email, String razinaPrava) {

		Map<String, String> greska = new HashMap<>();

		if (korisnickoIme == null || korisnickoIme.isEmpty()) {
			greska.put("korisnickoime", "Korisničko ime je prazno");
		} else if (DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme) != null) {
			greska.put("korisnickoime", "Korisnicko ime je zauzeto");
		}
		if (ime == null || ime.length() == 0) {
			greska.put("ime", "Ime je prazno");
		}
		if (prezime == null || prezime.length() == 0) {
			greska.put("prezime", "Prezime je prazno");
		}
		if (email == null || !validirajEmail(email)) {
			greska.put("email", "Email nije valjan");
		} else if (DAOKorisnik.getDAO().dohvatiKorisnikaPoMailu(email) != null) {
			greska.put("email", "Email je već u upotrebi");
		}
		if (razinaPrava == null || razinaPrava.isEmpty()) {
			greska.put("prava", "Nije odabrana uloga");
		}
		if (lozinka == null || lozinka.length() < 8) {
			greska.put("lozinka", "Lozinka mora imati barem 8 znakova");
		} else if (!lozinka.equals(lozinkaPotvrda)) {
			greska.put("lozinkapotvrda", "Lozinke se ne podudaraju");
		}

		return greska;
	}

	public static Map<String, String> provjeriFormuPrijavljivanja(String korisnickoIme, String lozinka) {

		Map<String, String> greska = new HashMap<>();

		if (korisnickoIme == null || korisnickoIme.isEmpty()) {
			greska.put("korisnickoime", "Korisničko ime je prazno");
			return greska;
		}

		Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme);

		if (korisnik == null) {
			greska.put("korisnickoime", "Korisničko ime nije pronađeno u bazi");
			return greska;
		}

		if (lozinka == null || lozinka.length() < 8) {
			greska.put("lozinka", "Lozinka mora imati barem 8 znakova");
			return greska;
		}
		try {
			if (!PasswordHash.validatePassword(lozinka, korisnik.getLozinka())) {
				greska.put("lozinka", "Lozinka je netočna");
				return greska;
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ignorable) {

		}

		return greska;
	}

}
