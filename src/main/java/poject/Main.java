package poject;

import java.time.LocalDate;

import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;
import poject.classDatabase.Evento;
import poject.classDatabase.EventoTipo;
import poject.utils.EventoDAO;
import poject.utils.jpaUtil;

@Slf4j
public class Main {

	public static void main(String[] args) {

		/**
		 * NON FUNZIONA PERCHE MANCA LA PASSWORD CONTATTARMI PER RECUPERARLA:
		 * domdat03@gmail.com
		 **/
		System.out.println(
				"**********************************************************************************************");

		System.out.println();
		System.out.println("NON FUNZIONA PERCHE MANCA LA PASSWORD CONTATTAMI PER RECUPERARLA: domdat03@gmail.com");
		System.out.println();
		System.out.println(
				"**********************************************************************************************");
		System.out.println();

		// di seguito il codice
		EntityManagerFactory emf = jpaUtil.getEntityManagerFactory();

		EventoDAO eventoDao = new EventoDAO(emf);
		System.out.println();
		log.info("SALVA EVENTO");
		System.out.println();
//		SALVA EVENTO
		Evento test = new Evento("altro evento prova", LocalDate.now().plusWeeks(2), "non bello", EventoTipo.PUBBLICO,
				1000);
		eventoDao.save(test);
		System.out.println();
		log.info("CERCA IN BASE ALL UUID");
		System.out.println();
//		CERCA IN BASE ALL UUID
		Evento risultato = eventoDao.getById("8283f566-8d80-487d-8fd8-acd7ca91bc6d");
		log.info(risultato.toString());
		System.out.println();
		log.info("ELIMINA IN BASE ALL UUID");
		System.out.println();
//		ELIMINA IN BASE ALL UUID
		eventoDao.deleteById(test.getId().toString());

//		REFRESH 
		System.out.println();
		log.info("REFRESH");
		System.out.println();
		eventoDao.refresh("8283f566-8d80-487d-8fd8-acd7ca91bc6d");

		emf.close();
	}

}
