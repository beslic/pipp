package hr.fer.pipp.sza.webapp.dao;

import hr.fer.pipp.sza.webapp.dao.jpa.JPADAOAnketa;

public class DAOAnketa {

	private static IDAOAnketa daoAnketa = new JPADAOAnketa();
	
	public static IDAOAnketa getDAO() {
		return daoAnketa;
	}
	
}
