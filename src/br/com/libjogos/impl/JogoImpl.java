package br.com.libjogos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.libjogos.controller.ResourcePersistence;
import br.com.libjogos.dao.JogoDAO;
import br.com.libjogos.model.Jogo;

public class JogoImpl implements JogoDAO {

	@Override
	public void inserir(Jogo obj) {
		EntityManager em = ResourcePersistence.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			System.out.println("Jogo adicionado!");
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public void deletar(Jogo obj) {
		EntityManager em = ResourcePersistence.getEntityManager();
		try {
			em.getTransaction().begin();
			Jogo jogo = em.find(Jogo.class, obj.getIdJogo());
			em.remove(jogo);
			em.getTransaction().commit();
			System.out.println("Jogo removido!");
		} catch (Exception e) {
			System.err.println("Erro:: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jogo> listar(Jogo obj) {

		EntityManager em = ResourcePersistence.getEntityManager();
		List<Jogo> lista = new ArrayList<Jogo>();
		try {
			em.getTransaction().begin();
			if (obj.getTitulo() != null && !obj.getGenero().equals("")) {
				lista = em.createQuery("SELECT c FROM Jogo c WHERE c.titulo = :titulo AND c.genero = :genero ")
						.setParameter("titulo", obj.getTitulo()).setParameter("genero", obj.getGenero())
						.getResultList();
			} else if (obj.getTitulo() != null) {
				lista = em.createQuery("SELECT c FROM Jogo c WHERE c.titulo = :titulo ")
						.setParameter("titulo", obj.getTitulo()).getResultList();
			} else {
				lista = em.createQuery("SELECT c FROM Joho c ").getResultList();
			}
			em.getTransaction().commit();
			return lista;
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		} finally {
			em.close();
		}
		return lista;
	}

}
