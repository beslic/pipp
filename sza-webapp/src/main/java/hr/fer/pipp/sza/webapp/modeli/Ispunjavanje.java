package hr.fer.pipp.sza.webapp.modeli;

import java.util.ArrayList;
import java.util.Date;

public class Ispunjavanje {

	private String imeAnketar;
	private Anketa anketa;

	private Date vrijemeIspunjavanja;
	private float longitunde;
	private float latitude;

	private int idIspunjavanje;
	private ArrayList<Odgovor> odabraniOdgovor;

	
	
	public Ispunjavanje() {
		// TODO Auto-generated constructor stub
	}
	public Ispunjavanje(String imeAnketar, Anketa anketa, Date vrijemeIspunjavanja, float longitunde, float latitude,
			ArrayList<Odgovor> odabraniOdgovor) {
		super();
		this.imeAnketar = imeAnketar;
		this.anketa = anketa;
		this.vrijemeIspunjavanja = vrijemeIspunjavanja;
		this.longitunde = longitunde;
		this.latitude = latitude;
		this.odabraniOdgovor = odabraniOdgovor;
	}

	public String getImeAnketar() {
		return imeAnketar;
	}

	public void setImeAnketar(String imeAnketar) {
		this.imeAnketar = imeAnketar;
	}

	public Anketa getAnketa() {
		return anketa;
	}

	public void setAnketa(Anketa anketa) {
		this.anketa = anketa;
	}

	public Date getVrijemeIspunjavanja() {
		return vrijemeIspunjavanja;
	}

	public void setVrijemeIspunjavanja(Date vrijemeIspunjavanja) {
		this.vrijemeIspunjavanja = vrijemeIspunjavanja;
	}

	public float getLongitunde() {
		return longitunde;
	}

	public void setLongitunde(float longitunde) {
		this.longitunde = longitunde;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getIdIspunjavanje() {
		return idIspunjavanje;
	}

	public void setIdIspunjavanje(int idIspunjavanje) {
		this.idIspunjavanje = idIspunjavanje;
	}

	public ArrayList<Odgovor> getOdabraniOdgovor() {
		return odabraniOdgovor;
	}

	public void setOdabraniOdgovor(ArrayList<Odgovor> odabraniOdgovor) {
		this.odabraniOdgovor = odabraniOdgovor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idIspunjavanje;
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
		if (idIspunjavanje != other.idIspunjavanje)
			return false;
		return true;
	}

}
