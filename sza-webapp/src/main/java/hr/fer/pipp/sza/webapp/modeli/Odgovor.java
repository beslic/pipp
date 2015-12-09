package hr.fer.pipp.sza.webapp.modeli;

import java.util.Date;

public class Odgovor {

	private int idOdgovor;
	private Pitanje pitanje;
	private int rbrOdgovor;

	private String textOdgovor;

	
	public Odgovor() {
		// TODO Auto-generated constructor stub
	}
	public Odgovor(int rbrOdgovor, String textOdgovor, Pitanje pitanje) {
		this.pitanje = pitanje;
		this.textOdgovor = textOdgovor;
		this.rbrOdgovor = rbrOdgovor;
	}

	public int getIdOdgovor() {
		return idOdgovor;
	}

	public void setIdOdgovor(int idOdgovor) {
		this.idOdgovor = idOdgovor;
	}

	public Pitanje getPitanje() {
		return pitanje;
	}

	public void setPitanje(Pitanje pitanje) {
		this.pitanje = pitanje;
	}

	public int getRbrOdgovor() {
		return rbrOdgovor;
	}

	public void setRbrOdgovor(int rbrOdgovor) {
		this.rbrOdgovor = rbrOdgovor;
	}

	public String getTextOdgovor() {
		return textOdgovor;
	}

	public void setTextOdgovor(String textOdgovor) {
		this.textOdgovor = textOdgovor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idOdgovor;
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
		Odgovor other = (Odgovor) obj;
		if (idOdgovor != other.idOdgovor)
			return false;
		return true;
	}

}