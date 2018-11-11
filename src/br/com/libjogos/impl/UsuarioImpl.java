package br.com.libjogos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.libjogos.controller.ResourcePersistence;
import br.com.libjogos.dao.UsuarioDAO;
import br.com.libjogos.model.Usuario;

public class UsuarioImpl implements UsuarioDAO{

	@Override
	public void inserir(Usuario obj) {

			EntityManager em = ResourcePersistence.getEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(obj);
				em.getTransaction().commit();
				System.out.println("Usuário cadastrado com sucesso!");
			} catch (Exception e) {
				System.err.println("Erro: " + e.getMessage());
			} finally {
				em.close();
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listar(Usuario obj) {
		EntityManager em = ResourcePersistence.getEntityManager();
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			em.getTransaction().begin();
			if (obj.getNomeUsuario() != null && !obj.getSenha().equals("")) {
				lista = em.createQuery("SELECT c FROM Usuario c WHERE c.nomeUsuario = :nome AND c.senha = :senha ")
						.setParameter("nome", obj.getNomeUsuario()).setParameter("senha", obj.getSenha()).getResultList();
			} else if (obj.getNomeUsuario() != null) {
				lista = em.createQuery("SELECT c FROM Usuario c WHERE c.nomeUsuario = :nome ")
						.setParameter("nome", obj.getNomeUsuario()).getResultList();
			} else {
				lista = em.createQuery("SELECT c FROM Usuario c ").getResultList();
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
