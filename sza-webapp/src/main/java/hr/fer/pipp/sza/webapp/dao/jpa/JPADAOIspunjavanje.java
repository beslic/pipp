package hr.fer.pipp.sza.webapp.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;

import hr.fer.pipp.sza.webapp.dao.IDAOIspunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Ispunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public class JPADAOIspunjavanje implements IDAOIspunjavanje {

	@Override
	public Ispunjavanje spremiIspunjavanje(Ispunjavanje ispn) {
		EntityManager em = JPAEMProvider.getEntityManager().getEntityManagerFactory().createEntityManager();
		Anketa a = em.find(Anketa.class, ispn.getAnketa().getIdAnketa());
		em.getTransaction().begin();
		em.persist(ispn);
		a.getIspunjavanja().add(ispn);
		em.getTransaction().commit();
		em.close();
		return ispn;
	}

	@Override
	public Collection<Ispunjavanje> spremiIspunjavanja(Collection<Ispunjavanje> ispns) {
		EntityManager em = JPAEMProvider.getEntityManager().getEntityManagerFactory().createEntityManager();
		Anketa a = em.find(Anketa.class, ispns.iterator().next().getAnketa().getIdAnketa());
		em.getTransaction().begin();
		ispns.forEach(ispn -> {
			em.persist(ispn);
			a.getIspunjavanja().add(ispn);
		});
		em.getTransaction().commit();
		em.close();
		return ispns;
	}

	@Override
	public Collection<Ispunjavanje> dohvatiSvaIspunjavanja() {
		return JPAEMProvider.getEntityManager().createQuery("FROM Ispunjavanje", Ispunjavanje.class).getResultList();
	}

	@Override
	public Collection<Ispunjavanje> dohvatiIspunjavanjeAnkete(Anketa anketa) {
		return JPAEMProvider.getEntityManager()
				.createQuery("FROM Ispunjavanje I WHERE I.anketa = :a", Ispunjavanje.class).setParameter("a", anketa)
				.getResultList();
	}

	@Override
	public Collection<Ispunjavanje> dohvatiIspunjavanjeAnketara(Korisnik anketar) {
		return JPAEMProvider.getEntityManager()
				.createQuery("FROM Ispunjavanje I WHERE I.anketar = :a", Ispunjavanje.class).setParameter("a", anketar)
				.getResultList();
	}

	@Override
	public Ispunjavanje dohvatiIspunjavanje(Long id) {
		return JPAEMProvider.getEntityManager().find(Ispunjavanje.class, id);
	}

}
