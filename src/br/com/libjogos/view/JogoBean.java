package br.com.libjogos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.libjogos.dao.JogoDAO;
import br.com.libjogos.impl.JogoImpl;
import br.com.libjogos.model.Jogo;
import br.com.libjogos.model.Usuario;


@ManagedBean(name = "jogoBN")
@ViewScoped
public class JogoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517917873453212881L;

	private JogoDAO jd = new JogoImpl();
	private Jogo jogo = new Jogo();
	private Jogo jogoSelecionado = new Jogo();
	private List<Jogo> jogos = new ArrayList<Jogo>();
	private Usuario usu;

	public String selecionar() {
		String retorno = null;

		if (jogoSelecionado.getIdJogo() > 0) {
			adicionarMensagem("Jogo " + jogoSelecionado.getTitulo() + " foi selecionado. Poderá ser removido agora",
					null, null);
		}
		return retorno;
	}

	public String inserir() {
		String retorno = null;
		
		UsuarioBean ub = new UsuarioBean();
		usu = ub.usu;
		
		jogo.setUsuario(usu);
		jd.inserir(jogo);
		adicionarMensagem("Jogo adicionado.", null, null);
		jogo = new Jogo();

		return retorno;
	}

	public void pesquisar() {
		this.preparaPesquisar(jogoSelecionado);
		jogos = new ArrayList<Jogo>();
		if (jogoSelecionado.getTitulo().equals("")) {
			jogoSelecionado = new Jogo();
		}
		List<Jogo> lst = jd.listar(jogoSelecionado);
		if (lst.size() > 0) {
			for (Jogo jogo : lst) {
				jogos.add(jogo);
			}
		} else {
			adicionarMensagem("Nenhum jogo encontrado", null, null);
		}
	}

	public void preparaPesquisar(Jogo jogo) {
		if (jogo.getTitulo() == null) {
			jogo.setTitulo("");
		}
		if (jogo.getGenero() == null) {
			jogo.setGenero("");
		}

	}

	public String deletar() {
		String retorno = null;
		if (jogoSelecionado.getIdJogo() == 0) {
			adicionarMensagem("Selecione o jogo que deseja deletar", null, null);
		} else {
			jd.deletar(jogoSelecionado);
			adicionarMensagem("Jogo deletado.", null, null);
			jogos = new ArrayList<Jogo>();
			jogoSelecionado = new Jogo();
		}
		return retorno;
	}
	
	public String irAddJogo() {
		
		UsuarioBean ub = new UsuarioBean();
		usu = ub.usu;
		
		String retorno = null;
		jogo = new Jogo();
		jogo.setUsuario(usu);
		
		retorno = "/cadastrojogo";
		return retorno;
	}
	
	public String voltarLogado() {
		String retorno = null;
		jogo = new Jogo();
		retorno = "/logado";
		return retorno;
	}

	public void adicionarMensagem(String sumario, String detalhe, String pagina) {
		FacesMessage mensagem = new FacesMessage(sumario, detalhe);
		FacesContext.getCurrentInstance().addMessage(pagina, mensagem);
	}

	public JogoDAO getJd() {
		return jd;
	}

	public void setJd(JogoDAO jd) {
		this.jd = jd;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Jogo getJogoSelecionado() {
		return jogoSelecionado;
	}

	public void setJogoSelecionado(Jogo jogoSelecionado) {
		this.jogoSelecionado = jogoSelecionado;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

}