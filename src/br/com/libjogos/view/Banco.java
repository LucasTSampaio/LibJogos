package br.com.libjogos.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Banco {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("libjogos");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
	}

}
