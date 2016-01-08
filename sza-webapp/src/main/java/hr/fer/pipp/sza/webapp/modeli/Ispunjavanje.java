package hr.fer.pipp.sza.webapp.modeli;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ispunjavanja")
public class Ispunjavanje {

	@Id
	@GeneratedValue
	private int idIspunjavanje;

	@ManyToOne
	private Korisnik anketar;

	@ManyToOne
	private Anketa anketa;

	@Column(nullable = false)
	private Date vrijemeIspunjavanja;

	@Column(nullable = false)
	private float longitunde;

	@Column(nullable = false)
	private float latitude;

	@OneToMany (mappedBy = "ispunjavanje")
	private List<Odgovor> odabraniOdgovor;

	public Ispunjavanje() {
	}

	public Ispunjavanje(Korisnik anketar, Anketa anketa, Date vrijemeIspunjavanja, float longitunde, float latitude,
			List<Odgovor> odabraniOdgovor) {
		super();
		this.anketar = anketar;
		this.anketa = anketa;
		this.vrijemeIspunjavanja = vrijemeIspunjavanja;
		this.longitunde = longitunde;
		this.latitude = latitude;
		this.odabraniOdgovor = odabraniOdgovor;
	}

	public int getIdIspunjavanje() {
		return idIspunjavanje;
	}

	public void setIdIspunjavanje(int idIspunjavanje) {
		this.idIspunjavanje = idIspunjavanje;
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

	public List<Odgovor> getOdabraniOdgovor() {
		return odabraniOdgovor;
	}

	public void setOdabraniOdgovor(List<Odgovor> odabraniOdgovor) {
		this.odabraniOdgovor = odabraniOdgovor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anketa == null) ? 0 : anketa.hashCode());
		result = prime * result + ((anketar == null) ? 0 : anketar.hashCode());
		result = prime * result + idIspunjavanje;
		result = prime * result + ((vrijemeIspunjavanja == null) ? 0 : vrijemeIspunjavanja.hashCode());
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
		if (anketa == null) {
			if (other.anketa != null)
				return false;
		} else if (!anketa.equals(other.anketa))
			return false;
		if (anketar == null) {
			if (other.anketar != null)
				return false;
		} else if (!anketar.equals(other.anketar))
			return false;
		if (idIspunjavanje != other.idIspunjavanje)
			return false;
		if (vrijemeIspunjavanja == null) {
			if (other.vrijemeIspunjavanja != null)
				return false;
		} else if (!vrijemeIspunjavanja.equals(other.vrijemeIspunjavanja))
			return false;
		return true;
	}
	
	

	
	
}
