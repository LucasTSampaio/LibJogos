package br.com.libjogos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.libjogos.dao.UsuarioDAO;
import br.com.libjogos.impl.UsuarioImpl;
import br.com.libjogos.model.Usuario;

@ManagedBean(name = "usuarioBN")
@ViewScoped
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874118699290870120L;

	private UsuarioDAO ud = new UsuarioImpl();
	private Usuario usuario = new Usuario();
	private String confirmaSenha = null;
	public static Usuario usu;

	public String envia() {
		String retorno = null;
		List<Usuario> lst = ud.listar(usuario);
		if (lst.size() == 0) {
			adicionarMensagem("Usuário não encontrado", null, null);
		} else {

			for (Usuario u : lst) {
				usu = u;
			}

			retorno = "/logado";
		}
		return retorno;
	}

	public String inserir() {
		String retorno = null;
		if (!usuario.getSenha().equalsIgnoreCase(this.getConfirmaSenha())) {
			adicionarMensagem("Confirme a senha", null, null);
		} else {
			ud.inserir(usuario);
			adicionarMensagem("Usuário cadastrado com sucesso!", null, null);
			usuario = new Usuario();
		}
		return retorno;
	}

	public void adicionarMensagem(String sumario, String detalhe, String pagina) {
		FacesMessage mensagem = new FacesMessage(sumario, detalhe);
		FacesContext.getCurrentInstance().addMessage(pagina, mensagem);
	}

	public UsuarioDAO getUd() {
		return ud;
	}

	public void setUd(UsuarioDAO ud) {
		this.ud = ud;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}