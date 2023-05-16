package poject;

import java.time.LocalDate;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poject.classDatabase.Evento;
import poject.classDatabase.EventoTipo;
import poject.utils.EventoDAO;
import poject.utils.jpaUtil;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		EntityManagerFactory emf = jpaUtil.getEntityManagerFactory();

		EventoDAO eventoDao = new EventoDAO(emf);
//		SALVA EVENTO
		Evento test = new Evento("altro evento prova", LocalDate.now().plusWeeks(2), "non bello", EventoTipo.PUBBLICO,
				1000);
		eventoDao.save(test);

//		CERCA IN BASE ALL UUID
		Evento risultato = eventoDao.getById("8283f566-8d80-487d-8fd8-acd7ca91bc6d");
		logger.info(risultato.toString());

//		ELIMINA IN BASE ALL UUID
		eventoDao.deleteById(test.getId().toString());
		emf.close();
	}

}
