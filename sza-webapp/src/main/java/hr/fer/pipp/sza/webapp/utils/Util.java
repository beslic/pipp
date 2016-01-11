package hr.fer.pipp.sza.webapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public class Util {
	String string = "January 2, 2010";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
	LocalDate date = LocalDate.parse(string, formatter);
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

	public static Map<String, String> provjeriFormuPrijavljivanjaAnketara(String korisnickoIme, String lozinka) {

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
		} else if (!lozinka.equals(korisnik.getLozinka())) {
			greska.put("lozinka", "Lozinka je netočna");
			return greska;
		}

		return greska;
	}

	public static Map<String, String> provjeriFormuAnkete(String nazivAnketa, String opisAnketa, String aktivnaOd,
			String aktivnaDo, String brojPitanja) {
		
		Map<String, String> greska = new HashMap<>();
		
		if (nazivAnketa == null || nazivAnketa.isEmpty()) {
			greska.put("nazivAnketa", "Polje naziva ankete je prazno");
		}
		
		if (aktivnaOd == null || aktivnaOd.isEmpty()) {
			greska.put("aktivnaOd", "Polje ne smije biti prazno");
		} else if (!aktivnaOd.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			greska.put("aktivnaOd", "Format nije dobro zadan - dd/mm/gggg");
		}
		
		if (aktivnaDo == null || aktivnaDo.isEmpty()) {
			greska.put("aktivnaDo", "Polje ne smije biti prazno");
		} else if (!aktivnaOd.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			greska.put("aktivnaDo", "Format nije dobro zadan - dd/mm/gggg");
		}
		
		System.out.println(aktivnaOd);
		System.out.println(aktivnaDo);
		System.out.println(nazivAnketa);
		System.out.println(opisAnketa);
		System.out.println(brojPitanja);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
		LocalDate datumOd = LocalDate.parse(aktivnaOd, formatter);
		LocalDate datumDo = LocalDate.parse(aktivnaDo, formatter);
		
		if (!datumOd.isBefore(datumDo)) {
			greska.put("aktivnaOd", "Datum nije kronološki dobro zadan");
			greska.put("aktivnaDo", "Datum nije kronološki dobro zadan");
		}
		
		return greska;

	}

}
