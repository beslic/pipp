package hr.fer.pipp.sza.webapp.dao;

import java.util.List;

import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public interface IDAOAnketa {

	public List<Anketa> dohvatiPrivatneAnkete(Korisnik korisnik);

	public List<Anketa> dohvatiJavneAnkete();

	public Anketa dohvatiAnketu(long id);

	public Anketa spremiAnketu(Anketa anketa);

}