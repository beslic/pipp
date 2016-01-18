package hr.fer.pipp.sza.webapp.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import hr.fer.pipp.sza.webapp.dao.IDAOKorisnik;
import hr.fer.pipp.sza.webapp.modeli.Korisnik;

public class JPADAOKorisnik implements IDAOKorisnik {

	@Override
	public Korisnik spremiIzmjeneKorisnika(Korisnik korisnik) {
		EntityManager em = JPAEMProvider.getEntityManager().getEntityManagerFactory().createEntityManager();
		Korisnik k = em.find(Korisnik.class, korisnik.getId());
		em.getTransaction().begin();
		k.setIme(korisnik.getIme());
		k.setPrezime(korisnik.getPrezime());
		k.setEmail(korisnik.getEmail());
		k.setLozinka(korisnik.getLozinka());
		em.getTransaction().commit();
		em.close();
		return korisnik;
	}

	@Override
	public Korisnik spremiNovogKorisnika(Korisnik korisnik) {
		EntityManager em = JPAEMProvider.getEntityManager().getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(korisnik);
		em.getTransaction().commit();
		em.close();
		return korisnik;
	}

	@Override
	public Korisnik dohvatiKorisnika(String korisnickoIme) {
		Korisnik k = null;
		try {
			k = JPAEMProvider.getEntityManager()
					.createQuery("FROM Korisnik K WHERE K.korisnickoIme = :korisnickoime", Korisnik.class)
					.setParameter("korisnickoime", korisnickoIme).getSingleResult();
		} catch (NoResultException ingorable) {
		}
		return k;
	}

	@Override
	public Korisnik dohvatiKorisnikaPoMailu(String email) {
		Korisnik k = null;
		try {
			k = JPAEMProvider.getEntityManager().createQuery("FROM Korisnik K WHERE K.email = :email", Korisnik.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException ingorable) {
		}
		return k;
	}

	@Override
	public List<Korisnik> dohvatiSveKorisnike() {
		return JPAEMProvider.getEntityManager().createQuery("FROM Korisnik", Korisnik.class).getResultList();
	}

	@Override
	public List<Korisnik> dohvatiSveAnketare() {
		return JPAEMProvider.getEntityManager().createQuery("FROM Korisnik K WHERE K.razinaPrava = 2", Korisnik.class)
				.getResultList();
	}

}
