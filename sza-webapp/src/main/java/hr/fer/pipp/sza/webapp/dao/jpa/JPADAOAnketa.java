package hr.fer.pipp.sza.webapp.dao.jpa;

import java.util.List;

import hr.fer.pipp.sza.webapp.dao.DAOKorisnik;
import hr.fer.pipp.sza.webapp.dao.IDAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public class JPADAOAnketa implements IDAOAnketa {

	@Override
	public List<Anketa> dohvatiAnkete(boolean logiran) {
		String query = "FROM Anketa";
		if (!logiran) {
			query += " A WHERE A.jePrivatna = 'N'";
		}
		return JPAEMProvider.getEntityManager().createQuery(query, Anketa.class).getResultList();
	}

	@Override
	public List<Anketa> dohvatiOdKorisnika(String korisnickoIme) {
		Korisnik korisnik = DAOKorisnik.getDAO().dohvatiKorisnika(korisnickoIme);
		return korisnik.getAnketa();
	}

}
