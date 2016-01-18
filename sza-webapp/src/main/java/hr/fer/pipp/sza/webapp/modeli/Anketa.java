package hr.fer.pipp.sza.webapp.modeli;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ankete")
public class Anketa {

	@Id
	@GeneratedValue
	@Expose
	private long idAnketa;

	@ManyToOne
	private Korisnik vlasnik;

	@Expose
	@Column(nullable = false)
	private Date vrijemeIzrada;

	@Expose
	@Column(nullable = false)
	private String nazivAnketa;

	@Expose
	@Column
	private String opisAnketa;

	@Expose
	@Column
	@Type(type = "yes_no")
	private boolean jePrivatna;

	@Expose
	@Column(nullable = false)
	private Date aktivnaOd;

	@Expose
	@Column(nullable = false)
	private Date aktivnaDo;

	@Expose
	@Column
	private int brojPitanja;

	@Expose
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "anketa", orphanRemoval = true)
	private List<Pitanje> pitanja;

	@Expose
	@OneToMany(mappedBy = "anketa")
	private List<Ispunjavanje> ispunjavanja;

	@Expose
	@Type(type = "yes_no")
	private boolean aktivna;

	public Anketa() {
	}

	public Anketa(Korisnik vlasnik, Date vrijemeIzrada, String nazivAnketa, String opisAnketa, boolean jePrivatna,
			Date aktivnaOd, Date aktivnaDo, boolean aktivna) {
		super();
		this.vlasnik = vlasnik;
		this.vrijemeIzrada = vrijemeIzrada;
		this.nazivAnketa = nazivAnketa;
		this.opisAnketa = opisAnketa;
		this.jePrivatna = jePrivatna;
		this.aktivnaOd = aktivnaOd;
		this.aktivnaDo = aktivnaDo;
		this.aktivna = aktivna;
	}

	public long getIdAnketa() {
		return idAnketa;
	}

	public void setIdAnketa(long idAnketa) {
		this.idAnketa = idAnketa;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Date getVrijemeIzrada() {
		return vrijemeIzrada;
	}

	public void setVrijemeIzrada(Date vrijemeIzrada) {
		this.vrijemeIzrada = vrijemeIzrada;
	}

	public String getNazivAnketa() {
		return nazivAnketa;
	}

	public void setNazivAnketa(String nazivAnketa) {
		this.nazivAnketa = nazivAnketa;
	}

	public String getOpisAnketa() {
		return opisAnketa;
	}

	public void setOpisAnketa(String opisAnketa) {
		this.opisAnketa = opisAnketa;
	}

	public boolean isJePrivatna() {
		return jePrivatna;
	}

	public void setJePrivatna(boolean jePrivatna) {
		this.jePrivatna = jePrivatna;
	}

	public Date getAktivnaOd() {
		return aktivnaOd;
	}

	public void setAktivnaOd(Date aktivnaOd) {
		this.aktivnaOd = aktivnaOd;
	}

	public Date getAktivnaDo() {
		return aktivnaDo;
	}

	public void setAktivnaDo(Date aktivnaDo) {
		this.aktivnaDo = aktivnaDo;
	}

	public List<Pitanje> getPitanja() {
		return pitanja;
	}

	public void setPitanja(List<Pitanje> pitanja) {
		this.pitanja = pitanja;
	}

	public int getBrojPitanja() {
		return brojPitanja;
	}

	public void setBrojPitanja(int brojPitanja) {
		this.brojPitanja = brojPitanja;
	}

	public boolean isAktivna() {
		return aktivna;
	}

	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}

	public List<Ispunjavanje> getIspunjavanja() {
		return ispunjavanja;
	}

	public void setIspunjavanja(List<Ispunjavanje> ispunjavanja) {
		this.ispunjavanja = ispunjavanja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAnketa ^ (idAnketa >>> 32));
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
		Anketa other = (Anketa) obj;
		if (idAnketa != other.idAnketa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Anketa [vrijemeIzrada=" + vrijemeIzrada + ", nazivAnketa=" + nazivAnketa + ", opisAnketa=" + opisAnketa
				+ ", jePrivatna=" + jePrivatna + ", aktivnaOd=" + aktivnaOd + ", aktivnaDo=" + aktivnaDo
				+ ", brojPitanja=" + brojPitanja + ", pitanja=" + Arrays.toString(pitanja.toArray()) + "]";
	}

}
