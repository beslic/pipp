package hr.fer.pipp.sza.webapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;

import hr.fer.pipp.sza.webapp.dao.DAOAnketa;
import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;
import hr.fer.pipp.sza.webapp.modeli.Odgovor;
import hr.fer.pipp.sza.webapp.modeli.Pitanje;

public class Util {

	public final static String PRAVA_ANONIMNOG_KORISNIKA = "/prijava/|" + "/registracija/|" + "/ankete/|"
			+ "/ankete/[0-9]+-[\\p{L}+0-9\\-_]+/(rezultati/){0,1}";
	public final static String PRAVA_REGISTRIRANOG_KORISNIKA = "/korisnici/|" + "/korisnici/[\\p{L}+0-9_]+/|"
			+ "/korisnici/[\\p{L}+0-9_]+/postavke/|" + "/korisnici/[\\p{L}+0-9_]+/ankete/|"
			+ "/korisnici/[\\p{L}+0-9_]+/ankete/[0-9]+-[\\p{L}+0-9\\-_]+/(rezultati/){0,1}|"
			+ "/korisnici/[\\p{L}+0-9_]+/ankete/[0-9]+-\\p{L}+0-9\\-_]+/izmijeni/|"
			+ "/korisnici/[\\p{L}+0-9_]+/ankete/nova/|" + "/android/|" + "/anketari/|" + "/ankete/|" + "/ankete/json/|"
			+ "/ankete/[0-9]+-[\\p{L}+0-9\\-_]+/(rezultati/){0,1}|" + "/ankete/[0-9]+-/[\\p{L}+0-9\\-_]+/json/";

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

	public static Anketa provjeriFormuAnkete(MultivaluedMap<String, String> form, Map<String, String> greske) {
		provjeriGreske(form, greske);
		return dohvatiAnketu(form, greske);

	}

	private static void provjeriGreske(MultivaluedMap<String, String> form, Map<String, String> greske) {
		String nazivAnketa = form.getFirst("nazivAnketa");
		String aktivnaOd = form.getFirst("aktivnaOd");
		String aktivnaDo = form.getFirst("aktivnaDo");

		if (nazivAnketa == null || nazivAnketa.isEmpty()) {
			greske.put("nazivAnketa", "Nije zadan ispravan naziv ankete");
		}
		if (aktivnaOd == null || aktivnaOd.isEmpty()) {
			greske.put("aktivnaOd", "Nije zadan datum početka ankete");
		} else if (!aktivnaOd.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			greske.put("aktivnaOd", "Format nije dobro zadan - dd/mm/gggg");
			greske.put("aktivnaOdForma", aktivnaOd);
		}
		if (aktivnaDo == null || aktivnaDo.isEmpty()) {
			greske.put("aktivnaDo", "Nije zadan datum završetka ankete");
		} else if (!aktivnaOd.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			greske.put("aktivnaDo", "Format nije dobro zadan - dd/mm/gggg");
			greske.put("aktivnaDoForma", aktivnaDo);
		}
		if (!(greske.containsKey("aktivnaDo") || greske.containsKey("aktivnaOd"))) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
			LocalDate datumOd = LocalDate.parse(aktivnaOd, formatter);
			LocalDate datumDo = LocalDate.parse(aktivnaDo, formatter);
			if (datumOd.isAfter(datumDo)) {
				greske.put("kron", "Datum nije kronološki dobro zadan");
				greske.put("aktivnaDoForma", aktivnaDo);
				greske.put("aktivnaOoForma", aktivnaOd);
			}
		}
	}

	private static Anketa dohvatiAnketu(MultivaluedMap<String, String> form, Map<String, String> greske) {
		Anketa anketa = new Anketa();
		anketa.setNazivAnketa(form.getFirst("nazivAnketa"));
		anketa.setOpisAnketa(form.getFirst("opisAnketa"));
		anketa.setJePrivatna(("privatna".equals(form.getFirst("privatna"))) ? true : false);
		anketa.setPitanja(dohvatiPitanja(form, anketa));
		anketa.setBrojPitanja(anketa.getPitanja().size());
		anketa.setAnketari(dohvatiAnketare(form, anketa));
		if (greske.isEmpty()) {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			try {
				anketa.setAktivnaOd(format.parse(form.getFirst("aktivnaOd")));
			} catch (ParseException e) {
				greske.put("aktivnaOd", "Format nije dobro zadan - dd/mm/gggg");
			}
			try {
				anketa.setAktivnaDo(format.parse(form.getFirst("aktivnaDo")));
			} catch (ParseException e) {
				greske.put("aktivnaDo", "Format nije dobro zadan - dd/mm/gggg");
			}
			anketa.setAktivna(provjeraAktivnosti(anketa, date));
			anketa.setVrijemeIzrada(date);
		}
		return anketa;
	}

	private static List<Korisnik> dohvatiAnketare(MultivaluedMap<String, String> form, Anketa anketa) {
		List<Korisnik> anketari = new ArrayList<>();
		for (String s : form.keySet()) {
			if (s.matches("anketar-[0-9]+")) {
				anketari.add(DAOKorisnik.getDAO().dohvatiKorisnika(form.getFirst(s)));
			}
		}
		return anketari;
	}

	private static List<Pitanje> dohvatiPitanja(MultivaluedMap<String, String> form, Anketa anketa) {
		Set<String> pitanjaId = dohvatiId(form, "pitanje[0-9]+");
		List<Pitanje> pitanja = new ArrayList<>();
		for (String p : pitanjaId) {
			String pit = form.getFirst(p);
			if (pit == null || pit.length() == 0) {
				continue;
			}
			Pitanje pitanje = new Pitanje();
			pitanje.setRbrPitanje(Integer.parseInt(p.replaceFirst("pitanje", "")));
			List<Odgovor> odgovori = dohvatiOdgovore(form, pitanje);
			pitanje.setOdgovor(odgovori);
			pitanje.setTextPitanje(pit);
			pitanje.setAnketa(anketa);
			pitanja.add(pitanje);
		}
		Collections.sort(pitanja, (p1, p2) -> Integer.compare(p1.getRbrPitanje(), p2.getRbrPitanje()));
		return pitanja;
	}

	private static List<Odgovor> dohvatiOdgovore(MultivaluedMap<String, String> form, Pitanje pitanje) {
		Set<String> odgovoriId = dohvatiId(form, "pitanje" + pitanje.getRbrPitanje() + "-odgovor[0-9]+");
		List<Odgovor> odgovori = new ArrayList<>();
		for (String o : odgovoriId) {
			String odg = form.getFirst(o);
			if (odg == null || odg.length() == 0) {
				continue;
			}
			Odgovor odgovor = new Odgovor();
			odgovor.setRbrOdgovor(Integer.parseInt(o.split("-")[1].replaceFirst("odgovor", "")));
			odgovor.setTextOdgovor(odg);
			odgovor.setPitanje(pitanje);
			odgovori.add(odgovor);
		}
		Collections.sort(odgovori, (o1, o2) -> Integer.compare(o1.getRbrOdgovor(), o2.getRbrOdgovor()));
		return odgovori;
	}

	private static Set<String> dohvatiId(MultivaluedMap<String, String> form, String regex) {
		Set<String> id = new LinkedHashSet<>();
		for (String key : form.keySet()) {
			if (key.matches(regex)) {
				id.add(key);
			}
		}
		return id;
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

	public static void provjeraPrivatnostiAnkete(ContainerRequestContext requestContext, HttpServletRequest req,
			UriInfo uri) {
		String idNazivAnketa = uri.getPathSegments().get(uri.getPathSegments().size() - 2).toString();
		Anketa a = DAOAnketa.getDAO().dohvatiAnketu(Integer.parseInt(idNazivAnketa.split("-")[0]));
		if (a != null) {
			if (a.isJePrivatna()) {
				Korisnik k = (Korisnik) req.getSession().getAttribute("korisnik");
				if (k == null) {
					requestContext.abortWith(Util.r404());
				}
				if (!k.equals(a.getVlasnik())) {
					requestContext.abortWith(Util.r403());
				}
			}
			req.setAttribute("anketa", a);
			return;
		} else {
			requestContext.abortWith(Util.r404());
		}
	}

	public static boolean provjeraAktivnosti(Anketa anketa, Date date) {
		return (anketa != null) ? date.after(anketa.getAktivnaOd()) && date.before(anketa.getAktivnaDo()) : false;
	}

	public static String formatDatum(Date datum) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(datum);
	}

	public static Response r404() {
		return Response.ok(new Viewable("/404")).status(Status.NOT_FOUND).build();
	}

	public static Response r403() {
		return Response.ok(new Viewable("/403")).status(Status.FORBIDDEN).build();
	}

	public static void setAktivno(HttpServletRequest req, String naziv) {
		req.setAttribute(naziv, "class=\"active\"");
	}

}
