package hr.fer.pipp.sza.webapp.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import hr.fer.pipp.sza.webapp.dao.IDAOAnketa;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public class JPADAOAnketa implements IDAOAnketa {

	@Override
	public Anketa dohvatiAnketu(long id) {
		Anketa anketa = null;
		try {
			anketa = JPAEMProvider.getEntityManager().createQuery("FROM Anketa A WHERE A.id = :id", Anketa.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException ingorable) {
		}
		return anketa;
	}

	@Override
	public Anketa spremiAnketu(Anketa anketa) {
		EntityManager em = JPAEMProvider.getEntityManager().getEntityManagerFactory().createEntityManager();
		Korisnik k = em.find(Korisnik.class, anketa.getVlasnik().getId());
		em.getTransaction().begin();
		em.persist(anketa);
		k.getAnketa().add(anketa);
		em.getTransaction().commit();
		em.close();
		return anketa;
	}

	@Override
	public List<Anketa> dohvatiPrivatneAnkete(Korisnik korisnik) {
		return JPAEMProvider.getEntityManager().createQuery("FROM Anketa A WHERE A.vlasnik = :v", Anketa.class)
				.setParameter("v", korisnik).getResultList();
	}

	@Override
	public List<Anketa> dohvatiJavneAnkete() {
		return JPAEMProvider.getEntityManager().createQuery("FROM Anketa A WHERE A.jePrivatna = 'N'", Anketa.class)
				.getResultList();
	}

	public List<Anketa> dohvatiAnkete(boolean logiran) {
		return JPAEMProvider.getEntityManager()
				.createQuery("FROM Anketa A" + ((!logiran) ? "WHERE A.jePrivatna = 'N'" : ""), Anketa.class)
				.getResultList();
	}

}
