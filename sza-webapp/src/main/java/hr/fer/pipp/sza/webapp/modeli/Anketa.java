package hr.fer.pipp.sza.webapp.modeli;

import java.util.Date;
import java.util.List;

public class Anketa {

	private int idAnketa;
	private Korisnik vlasnikAnketa;
	private Date vrijemeIzrada;

	private String nazivAnketa;
	private String opisAnketa;
	private boolean jePrivatna;
	private Date aktivnaOd;
	private Date aktivnaDo;

	private List<Korisnik> anketari;
	private List<Pitanje> pitanje;
	private List<Ispunjavanje> ispunjavanje;

	public Anketa() {
		// defualt
	}

	public Anketa(Korisnik vlasnikAnketa, Date vrijemeIzrada, String nazivAnketa, String opisAnketa, boolean jePrivatna,
			Date aktivnaOd, Date aktivnaDo) {
		super();
		this.vlasnikAnketa = vlasnikAnketa;
		this.vrijemeIzrada = vrijemeIzrada;
		this.nazivAnketa = nazivAnketa;
		this.opisAnketa = opisAnketa;
		this.jePrivatna = jePrivatna;
		this.aktivnaOd = aktivnaOd;
		this.aktivnaDo = aktivnaDo;
	}

	public int getIdAnketa() {
		return idAnketa;
	}

	public void setIdAnketa(int idAnketa) {
		this.idAnketa = idAnketa;
	}

	public Korisnik getVlasnikAnketa() {
		return vlasnikAnketa;
	}

	public void setVlasnikAnketa(Korisnik vlasnikAnketa) {
		this.vlasnikAnketa = vlasnikAnketa;
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

	public List<Korisnik> getAnketari() {
		return anketari;
	}

	public void setAnketari(List<Korisnik> anketari) {
		this.anketari = anketari;
	}

	public List<Ispunjavanje> getIspunjavanje() {
		return ispunjavanje;
	}

	public void setIspunjavanje(List<Ispunjavanje> ispunjavanje) {
		this.ispunjavanje = ispunjavanje;
	}

	public List<Pitanje> getPitanje() {
		return pitanje;
	}

	public void setPitanje(List<Pitanje> pitanje) {
		this.pitanje = pitanje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAnketa;
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

}
