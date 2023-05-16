package poject.utils;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import lombok.extern.slf4j.Slf4j;
import poject.classDatabase.Evento;
import poject.exceptions.NotFoundException;

@Slf4j
public class EventoDAO {
	private EntityManagerFactory emf;

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
		log.info("salvataggio avvenuto con successo");
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
				log.info("elemento con id: " + id + " eliminato con successo");
			} else {
				throw new NotFoundException("evento non trovato");
			}
			transazione.commit();
		} catch (NotFoundException e) {
			log.error(e.getMessage());
		} finally {
			em.close();
		}

	}

	public void refresh(String id) {
		EntityManager em = emf.createEntityManager();

		Evento evento = em.find(Evento.class, UUID.fromString(id));
		evento.setTitolo("sono stato modificato");
		log.info("PRE REFRESH");
		log.info(evento.toString());
		em.refresh(evento);
		log.info("POST REFRESH");
		log.info(evento.toString());

		em.close();
	}
}
