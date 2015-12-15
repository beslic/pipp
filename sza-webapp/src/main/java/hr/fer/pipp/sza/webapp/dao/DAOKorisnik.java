package hr.fer.pipp.sza.webapp.dao;

import hr.fer.pipp.sza.webapp.dao.jpa.JPADAOKorisnik;

public class DAOKorisnik {
	
	private static IDAOKorisnik daoKorisnik = new JPADAOKorisnik();
	
	public static IDAOKorisnik getDAO() {
		return daoKorisnik;
	}

}
