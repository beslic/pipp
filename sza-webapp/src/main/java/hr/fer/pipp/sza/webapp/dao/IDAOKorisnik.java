package hr.fer.pipp.sza.webapp.dao;

import java.util.List;

import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public interface IDAOKorisnik {

	public Korisnik spremiIzmjeneKorisnika(Korisnik korisnik);
	
	public Korisnik spremiNovogKorisnika(Korisnik korisnik);
	
	public Korisnik dohvatiKorisnika(String korisnickoIme);
	
	public List<Korisnik> dohvatiSveKorisnike();

	public Korisnik dohvatiKorisnikaPoMailu(String email);
}
