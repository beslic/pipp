package hr.fer.pipp.sza.webapp.dao.jpa;

import java.util.Date;
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
		return provjeraAktivnosti(anketa, new Date());
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
		List<Anketa> ankete = JPAEMProvider.getEntityManager()
				.createQuery("FROM Anketa A WHERE A.vlasnik = :v", Anketa.class).setParameter("v", korisnik)
				.getResultList();
		Date d = new Date();
		for (Anketa a : ankete) {
			provjeraAktivnosti(a, d);
		}
		return ankete;
	}

	@Override
	public List<Anketa> dohvatiJavneAnkete() {
		List<Anketa> ankete = JPAEMProvider.getEntityManager()
				.createQuery("FROM Anketa A WHERE A.jePrivatna = 'N'", Anketa.class).getResultList();
		Date d = new Date();
		for (Anketa a : ankete) {
			provjeraAktivnosti(a, d);
		}
		return ankete;
	}

	@Override
	public Anketa spremiIzmjeneAnkete(Anketa anketa) {
		EntityManager em = JPAEMProvider.getEntityManager().getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Anketa a = em.find(Anketa.class, anketa.getIdAnketa());
		a.setAktivna(anketa.isAktivna());
		a.setAktivnaDo(anketa.getAktivnaDo());
		a.setAktivnaOd(anketa.getAktivnaOd());
		a.setBrojPitanja(anketa.getBrojPitanja());
		a.setJePrivatna(anketa.isJePrivatna());
		a.setNazivAnketa(anketa.getNazivAnketa());
		a.setOpisAnketa(anketa.getOpisAnketa());
		a.setPitanja(anketa.getPitanja());
		// TODO dodati izmjene anketara
		em.getTransaction().commit();
		em.close();
		return anketa;
	}

	private Anketa provjeraAktivnosti(Anketa anketa, Date date) {
		if (anketa != null) {
			if (date.after(anketa.getAktivnaOd()) && date.before(anketa.getAktivnaDo())) {
				// mora biti aktivna
				if (!anketa.isAktivna()) {
					anketa.setAktivna(true);
					spremiIzmjeneAnkete(anketa);
				}
			} else {
				// mora biti neaktivna
				if (anketa.isAktivna()) {
					anketa.setAktivna(false);
					spremiIzmjeneAnkete(anketa);
				}
			}
		}
		return anketa;
	}

}
