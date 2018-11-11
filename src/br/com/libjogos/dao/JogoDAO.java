package br.com.libjogos.dao;

import java.util.List;

import br.com.libjogos.model.Jogo;

public interface JogoDAO {
	
	public void inserir(Jogo obj);

	public void deletar(Jogo obj);

	public List<Jogo> listar(Jogo obj);
}
