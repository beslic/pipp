package hr.fer.pipp.sza.webapp.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gps")
public class GPSPozicija {

	@Id
	@GeneratedValue
	private long id;

	private double visina;

	private double sirina;

	public GPSPozicija() {

	}

	public GPSPozicija(double visina, double sirina) {
		super();
		this.visina = visina;
		this.sirina = sirina;
	}

	public double getVisina() {
		return visina;
	}

	public void setVisina(double visina) {
		this.visina = visina;
	}

	public double getSirina() {
		return sirina;
	}

	public void setSirina(double sirina) {
		this.sirina = sirina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(sirina);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(visina);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GPSPozicija other = (GPSPozicija) obj;
		if (Double.doubleToLongBits(sirina) != Double.doubleToLongBits(other.sirina))
			return false;
		if (Double.doubleToLongBits(visina) != Double.doubleToLongBits(other.visina))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GPSPozicija [visina=" + visina + ", sirina=" + sirina + "]";
	}

}
