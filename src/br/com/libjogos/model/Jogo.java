package br.com.libjogos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Jogo")
@Table(name = "T_JOGO")
public class Jogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6797103371227704894L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_JOGO")
	private int idJogo;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "GENERO")
	private String genero;

	@Column(name = "ANO_LANCAMENTO")
	private String anoLancamento;

	@Column(name = "ESTADO_JOGO")
	private String estadoJogo;

	@Column(name = "PLATAFORMA")
	private String plataforma;

	// relacionamento
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_JOGO_USUARIO"), name = "ID_USUARIO", insertable = false, updatable = false)
	private Usuario usuario = new Usuario();

	// getters e setters
	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getEstadoJogo() {
		return estadoJogo;
	}

	public void setEstadoJogo(String estadoJogo) {
		this.estadoJogo = estadoJogo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
