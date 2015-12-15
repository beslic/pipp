package hr.fer.pipp.sza.webapp.dao.jpa;

import javax.persistence.EntityManager;

import hr.fer.pipp.sza.webapp.dao.DAOException;

public class JPAEMProvider {
	
	private static ThreadLocal<LocalData> locals = new ThreadLocal<>();
	
	public static EntityManager getEntityManager() {
		LocalData ldata = locals.get();
		if (ldata == null) {
			ldata = new LocalData();
			ldata.em = JPAEMFProvider.getEMF().createEntityManager();
			ldata.em.getTransaction().begin();
		}
		return ldata.em;
	}
	
	public static void close() throws DAOException {
		final LocalData ldata = locals.get();
		if (ldata == null) {
			return;
		}
		DAOException dex = null;
		try {
			ldata.em.getTransaction().commit();
		} catch (final Exception ex) {
			dex = new DAOException("Unable to close entity manager", ex);
		}
		locals.remove();
		if (dex != null) {
			throw dex;
		}
	}
	
	private static class LocalData {
		EntityManager em;
	}
}
