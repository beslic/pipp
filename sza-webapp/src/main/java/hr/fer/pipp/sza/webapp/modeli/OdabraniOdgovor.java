package hr.fer.pipp.sza.webapp.modeli;

import java.util.Date;

public class OdabraniOdgovor {

	private int idOdabraniOdgovor;
	private Odgovor odgovor;
	private Ispunjavanje ispunjavanje;


	
	
	public OdabraniOdgovor() {
		// TODO Auto-generated constructor stub
	}
	public OdabraniOdgovor(Odgovor odgovor, Ispunjavanje ispunjavanje) {
		super();
		this.odgovor = odgovor;
		this.ispunjavanje = ispunjavanje;
	}

	public int getIdOdabraniOdgovor() {
		return idOdabraniOdgovor;
	}

	public void setIdOdabraniOdgovor(int idOdabraniOdgovor) {
		this.idOdabraniOdgovor = idOdabraniOdgovor;
	}

	public Odgovor getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(Odgovor odgovor) {
		this.odgovor = odgovor;
	}

	public Ispunjavanje getIspunjavanje() {
		return ispunjavanje;
	}

	public void setIspunjavanje(Ispunjavanje ispunjavanje) {
		this.ispunjavanje = ispunjavanje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idOdabraniOdgovor;
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
		OdabraniOdgovor other = (OdabraniOdgovor) obj;
		if (idOdabraniOdgovor != other.idOdabraniOdgovor)
			return false;
		return true;
	}

}
