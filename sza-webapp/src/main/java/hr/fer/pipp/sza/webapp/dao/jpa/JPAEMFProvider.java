package hr.fer.pipp.sza.webapp.dao.jpa;

import javax.persistence.EntityManagerFactory;

public class JPAEMFProvider {
	
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEMF() {
		return emf;
	}
	
	public static void setEMF(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}

}
