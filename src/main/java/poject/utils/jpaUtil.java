package poject.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class jpaUtil {
	private static final EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("esercizioG1");
		} catch (Throwable ex) {
			System.err.println("Initial EntityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

}
