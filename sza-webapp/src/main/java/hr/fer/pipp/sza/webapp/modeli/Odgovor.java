package hr.fer.pipp.sza.webapp.modeli;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "odgovori")
public class Odgovor {

	@Id
	@GeneratedValue
	@Expose
	private long idOdgovor;

	@ManyToOne
	private Pitanje pitanje;

	@Expose
	@Column(nullable = false)
	private int rbrOdgovor;

	@Expose
	@Column(nullable = false)
	private String textOdgovor;

	@ManyToMany(mappedBy = "odgovori")
	private Collection<Ispunjavanje> ispunjavanja;

	public Odgovor() {
	}

	public Odgovor(int rbrOdgovor, String textOdgovor, Pitanje pitanje) {
		this.pitanje = pitanje;
		this.textOdgovor = textOdgovor;
		this.rbrOdgovor = rbrOdgovor;
	}

	public long getIdOdgovor() {
		return idOdgovor;
	}

	public void setIdOdgovor(long idOdgovor) {
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

	public Collection<Ispunjavanje> getIspunjavanja() {
		return ispunjavanja;
	}

	public void setIspunjavanja(Collection<Ispunjavanje> ispunjavanja) {
		this.ispunjavanja = ispunjavanja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idOdgovor ^ (idOdgovor >>> 32));
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

	@Override
	public String toString() {
		return "Odgovor [rbrOdgovor=" + rbrOdgovor + ", textOdgovor=" + textOdgovor + "]";
	}

}
