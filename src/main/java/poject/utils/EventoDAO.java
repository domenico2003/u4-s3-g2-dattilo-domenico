package poject.utils;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poject.classDatabase.Evento;
import poject.exceptions.NotFoundException;

public class EventoDAO {
	private EntityManagerFactory emf;
	private static Logger logger = LoggerFactory.getLogger(EventoDAO.class);

	public EventoDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void save(Evento e) {

		EntityManager em = emf.createEntityManager();

		EntityTransaction transazione = em.getTransaction();
		transazione.begin();

		em.persist(e);

		transazione.commit();

		em.close();
		logger.info("salvataggio avvenuto con successo");
	}

	public Evento getById(String id) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction transazione = em.getTransaction();
		transazione.begin();

		Evento risultatoRicerca = em.find(Evento.class, UUID.fromString(id));

		transazione.commit();

		em.close();

		return risultatoRicerca;
	}

	public void deleteById(String id) {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction transazione = em.getTransaction();
			transazione.begin();

			Evento evento = em.find(Evento.class, UUID.fromString(id));

			if (evento != null) {
				em.remove(evento);
				logger.info("elemento con id: " + id + " eliminato con successo");
			} else {
				throw new NotFoundException("evento non trovato");
			}
			transazione.commit();
		} catch (NotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			em.close();
		}

	}
}
