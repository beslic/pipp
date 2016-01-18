package hr.fer.pipp.sza.webapp.dao;

import java.util.Collection;

import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Ispunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public interface IDAOIspunjavanje {

	public Ispunjavanje spremiIspunjavanje(Ispunjavanje ispn);

	public Collection<Ispunjavanje> spremiIspunjavanja(Collection<Ispunjavanje> ispn);

	public Collection<Ispunjavanje> dohvatiSvaIspunjavanja();

	public Collection<Ispunjavanje> dohvatiIspunjavanjeAnkete(Anketa anketa);

	public Ispunjavanje dohvatiIspunjavanje(Long id);

	Collection<Ispunjavanje> dohvatiIspunjavanjeAnketara(Korisnik anketar);

}
