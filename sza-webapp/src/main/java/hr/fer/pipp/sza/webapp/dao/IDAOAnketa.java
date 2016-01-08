package hr.fer.pipp.sza.webapp.dao;

import java.util.List;

import hr.fer.pipp.sza.webapp.modeli.Anketa;

public interface IDAOAnketa {

	public List<Anketa> dohvatiAnkete(boolean logiran);
	
	public List<Anketa> dohvatiOdKorisnika(String korisnickoIme);
	
	public Anketa dohvatiAnketu(int id);
	
	public Anketa spremiAnketu(Anketa anketa);
	
}
