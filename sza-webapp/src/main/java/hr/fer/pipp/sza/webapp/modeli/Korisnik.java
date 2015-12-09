package hr.fer.pipp.sza.webapp.modeli;

import java.util.List;

public class Korisnik {

	private int idKorisnik;

	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String lozinka;
	private String email;
	private int razinaPrava;
	private boolean aktivan;

	private List<Anketa> anketa;

	
	public Korisnik() {
		// TODO Auto-generated constructor stub
	}
	public Korisnik(String korisnickoIme, String ime, String prezime, String lozinka, String email, int razinaPrava,
			boolean aktivan) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.email = email;
		this.razinaPrava = razinaPrava;
		this.aktivan = aktivan;
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRazinaPrava() {
		return razinaPrava;
	}

	public void setRazinaPrava(int razinaPrava) {
		this.razinaPrava = razinaPrava;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public List<Anketa> getAnketa() {
		return anketa;
	}

	public void setAnketa(List<Anketa> anketa) {
		this.anketa = anketa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idKorisnik;
		result = prime * result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idKorisnik != other.idKorisnik)
			return false;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		return true;
	}

}
