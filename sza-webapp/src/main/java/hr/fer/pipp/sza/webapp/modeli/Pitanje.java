package hr.fer.pipp.sza.webapp.modeli;

import java.util.Date;
import java.util.List;

public class Pitanje {

	private int idPitanje;
	private Anketa anketa;
	private int rbrPitanje;
	private String textPitanje;
	private List<Odgovor> odgovor;

	
	
	public Pitanje() {
		// TODO Auto-generated constructor stub
	}
	public Pitanje(int rbrPitanje, String textPitanje, Anketa anketa) {

		this.anketa = anketa;
		this.rbrPitanje = rbrPitanje;
		this.textPitanje = textPitanje;
	}

	public List<Odgovor> getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(List<Odgovor> odgovor) {
		this.odgovor = odgovor;
	}

	public int getIdPitanje() {
		return idPitanje;
	}

	public void setIdPitanje(int idPitanje) {
		this.idPitanje = idPitanje;
	}

	public Anketa getAnketa() {
		return anketa;
	}

	public void setAnketa(Anketa anketa) {
		this.anketa = anketa;
	}

	public int getRbrPitanje() {
		return rbrPitanje;
	}

	public void setRbrPitanje(int rbrPitanje) {
		this.rbrPitanje = rbrPitanje;
	}

	public String getTextPitanje() {
		return textPitanje;
	}

	public void setTextPitanje(String textPitanje) {
		this.textPitanje = textPitanje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPitanje;
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
		Pitanje other = (Pitanje) obj;
		if (idPitanje != other.idPitanje)
			return false;
		return true;
	}

}
