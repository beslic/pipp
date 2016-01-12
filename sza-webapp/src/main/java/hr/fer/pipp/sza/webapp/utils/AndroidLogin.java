package hr.fer.pipp.sza.webapp.utils;

public class AndroidLogin {

	private String ime;

	private String lozinka;

	public AndroidLogin() {

	}

	public AndroidLogin(String ime, String lozinka) {
		super();
		this.ime = ime;
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
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
		AndroidLogin other = (AndroidLogin) obj;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AndroidLogin [korisnickoIme=" + ime + ", lozinka=" + lozinka + "]";
	}

}
