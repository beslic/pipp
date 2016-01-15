package hr.fer.pipp.sza.webapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
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

	public static Map<String, String> provjeriFormuPrijavljivanjaAnketara(String korisnickoIme, String lozinka)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		Map<String, String> greska = new HashMap<>();

		if (korisnickoIme == null || korisnickoIme.isEmpty()) {
			greska.put("ime", "Korisničko ime je prazno");
			return greska;
		}

		Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme);

		if (korisnik == null) {
			greska.put("ime", "Korisničko ime nije pronađeno u bazi");
			return greska;
		}

		if (lozinka == null || lozinka.length() < 8) {
			greska.put("lozinka", "Lozinka mora imati barem 8 znakova");
			return greska;
		} else if (!PasswordHash.validatePassword(lozinka, korisnik.getLozinka())) {
			greska.put("lozinka", "Lozinka je netočna");
			return greska;
		}

		return greska;
	}

	public static Map<String, String> provjeriFormuAnkete(String nazivAnketa, String opisAnketa, String aktivnaOd,
			String aktivnaDo) {

		Map<String, String> greske = new HashMap<>();

		if (nazivAnketa == null || nazivAnketa.isEmpty()) {
			greske.put("nazivAnketa", "Nije zadan ispravan naziv ankete");
		}

		if (aktivnaOd == null || aktivnaOd.isEmpty()) {
			greske.put("aktivnaOd", "Nije zadan datum početka ankete");
		} else if (!aktivnaOd.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			greske.put("aktivnaOd", "Format nije dobro zadan - dd/mm/gggg");
		}

		if (aktivnaDo == null || aktivnaDo.isEmpty()) {
			greske.put("aktivnaDo", "Nije zadan datum završetka ankete");
		} else if (!aktivnaOd.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			greske.put("aktivnaDo", "Format nije dobro zadan - dd/mm/gggg");
		}

		if (greske.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
			LocalDate datumOd = LocalDate.parse(aktivnaOd, formatter);
			LocalDate datumDo = LocalDate.parse(aktivnaDo, formatter);

			if (datumOd.isAfter(datumDo)) {
				greske.put("aktivnaOdKron", "Datum nije kronološki dobro zadan");
				greske.put("aktivnaDoKron", "Datum nije kronološki dobro zadan");
			}
		}

		return greske;
	}

	public static Map<String, String> provjeriFormuPostavkiKorisnika(String ime, String prezime, String email) {

		Map<String, String> greska = new HashMap<>();

		if (ime == null || ime.isEmpty()) {
			greska.put("ime", "Ime je prazno");
		}

		if (prezime == null || prezime.isEmpty()) {
			greska.put("prezime", "Prezime je prazno");
		}

		if (email == null || email.isEmpty()) {
			greska.put("email", "Email je prazan");
			return greska;
		} else if (!validirajEmail(email)) {
			greska.put("email", "Email nije u dobrom formatu");
			return greska;
		}

		Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnikaPoMailu(email);

		if (korisnik != null && !korisnik.getEmail().equals(email)) {
			greska.put("email", "Email je vec u upotrebi");
		}

		return greska;
	}

	public static Map<String, String> provjeriFormuPromjeneLozinke(String staraLozinka, String novaLozinka,
			String novaLozinkaPotvrda) {
		Map<String, String> greska = new HashMap<>();

		if (staraLozinka == null || staraLozinka.isEmpty()) {
			greska.put("staralozinka", "Polje stare lozinke je prazno");
		}
		if (novaLozinka == null || novaLozinka.isEmpty()) {
			greska.put("novalozinka", "Polje nove lozinke je prazno");
		}
		if (novaLozinkaPotvrda == null || novaLozinkaPotvrda.isEmpty()) {
			greska.put("novalozinkapotvrda", "Polje potvrde lozinke je prazno");
		} else if (!novaLozinkaPotvrda.equals(novaLozinka)) {
			greska.put("novalozinka", "Lozinke se ne podudaraju");
			greska.put("novalozinkapotvrda", "Lozinke se ne podudaraju");
		}

		return greska;
	}

	public static boolean provjeraAktivnosti(Anketa anketa, Date date) {
		if (anketa != null) {
			if (date.after(anketa.getAktivnaOd()) && date.before(anketa.getAktivnaDo())) {
				// mora biti aktivna
				return true;
			} else {
				// mora biti neaktivna
				return false;
			}
		} else {
			return false;
		}
	}

}
