package hr.fer.pipp.sza.webapp.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class OdabraniOdgovor {

	@Id
	@GeneratedValue
	private int idOdabraniOdgovor;
	
	private Ispunjavanje ispunjavanje;

	public OdabraniOdgovor() {
	}

	public OdabraniOdgovor(Odgovor odgovor, Ispunjavanje ispunjavanje) {
		super();
		this.ispunjavanje = ispunjavanje;
	}

	public int getIdOdabraniOdgovor() {
		return idOdabraniOdgovor;
	}

	public void setIdOdabraniOdgovor(int idOdabraniOdgovor) {
		this.idOdabraniOdgovor = idOdabraniOdgovor;
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
