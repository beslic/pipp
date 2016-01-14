package hr.fer.pipp.sza.webapp.modeli;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ispunjavanja")
public class Ispunjavanje {

	@Id
	@GeneratedValue
	@Expose
	private long id;

	@ManyToOne
	private Korisnik anketar;

	@Expose
	@ManyToOne
	private Anketa anketa;

	@Expose
	@Column(nullable = false)
	private Date vrijeme;

	@Expose
	@ManyToOne
	private GPSPozicija pozicija;

	@Expose
	@ManyToMany
	private Collection<Odgovor> odgovori;

	public Ispunjavanje() {

	}

	public Ispunjavanje(Korisnik anketar, Anketa anketa, GPSPozicija pozicija, Collection<Odgovor> odgovori) {
		super();
		this.anketar = anketar;
		this.anketa = anketa;
		this.pozicija = pozicija;
		this.odgovori = odgovori;
		this.vrijeme = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Korisnik getAnketar() {
		return anketar;
	}

	public void setAnketar(Korisnik anketar) {
		this.anketar = anketar;
	}

	public Anketa getAnketa() {
		return anketa;
	}

	public void setAnketa(Anketa anketa) {
		this.anketa = anketa;
	}

	public Date getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}

	public GPSPozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(GPSPozicija pozicija) {
		this.pozicija = pozicija;
	}

	public Collection<Odgovor> getOdgovori() {
		return odgovori;
	}

	public void setOdgovori(Collection<Odgovor> odgovori) {
		this.odgovori = odgovori;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Ispunjavanje other = (Ispunjavanje) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ispunjavanje [id=" + id + ", anketar=" + anketar.getKorisnickoIme() + ", anketa="
				+ anketa.getNazivAnketa() + ", vrijeme=" + vrijeme + ", pozicija=" + pozicija + "]";
	}

}
