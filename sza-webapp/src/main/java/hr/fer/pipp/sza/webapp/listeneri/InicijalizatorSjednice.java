package hr.fer.pipp.sza.webapp.listeneri;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import hr.fer.pipp.sza.webapp.dao.jpa.JPAEMFProvider;

@WebListener
public class InicijalizatorSjednice implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sza.database");
		sce.getServletContext().setAttribute("application.emf", emf);
		JPAEMFProvider.setEMF(emf);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		JPAEMFProvider.setEMF(null);
		EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("application.emf");
		if (emf != null) {
			emf.close();
		}
	}
	
}
