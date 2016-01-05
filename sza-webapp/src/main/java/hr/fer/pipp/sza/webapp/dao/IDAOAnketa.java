package hr.fer.pipp.sza.webapp.dao;

import java.util.List;

import hr.fer.pipp.sza.webapp.modeli.Anketa;

public interface IDAOAnketa {

	List<Anketa> dohvatiAnkete(boolean logiran);
	
	List<Anketa> dohvatiOdKorisnika(String korisnickoIme);
	
	Anketa dohvatiAnketu(int id);
	
}
