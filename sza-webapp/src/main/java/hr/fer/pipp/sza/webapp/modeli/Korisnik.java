package hr.fer.pipp.sza.webapp.modeli;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "korisnici")
public class Korisnik {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String ime;

	@Column(nullable = false)
	private String prezime;

	@Column(unique = true, nullable = false)
	private String korisnickoIme;

	@Column(nullable = false)
	private String lozinka;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private int razinaPrava;

	@Column
	@Type(type = "yes_no")
	private boolean aktivan;

	@OneToMany(mappedBy = "vlasnik")
	private List<Anketa> anketa;

	public Korisnik() {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		return true;
	}

}
