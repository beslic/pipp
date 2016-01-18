package hr.fer.pipp.sza.webapp.dao;

import hr.fer.pipp.sza.webapp.dao.jpa.JPADAOIspunjavanje;

public class DAOIspn {

	private static IDAOIspunjavanje daoIspn = new JPADAOIspunjavanje();

	public static IDAOIspunjavanje getDAO() {
		return daoIspn;
	}

}
