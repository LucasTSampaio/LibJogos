package br.com.libjogos.dao;

import java.util.List;

import br.com.libjogos.model.Usuario;

public interface UsuarioDAO {

	public void inserir(Usuario obj);
	
	public List<Usuario> listar(Usuario obj);

}
