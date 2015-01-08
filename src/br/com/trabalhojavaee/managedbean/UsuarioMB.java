package br.com.trabalhojavaee.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.trabalhojavaee.model.Usuario;
import br.com.trabalhojavaee.service.UsuarioService;

@ManagedBean (name="usuarioMB")
@ViewScoped
public class UsuarioMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4142246796136687908L;
	/** 
	 * Injecao para camada de negocio 
	 */
	@EJB
	private UsuarioService usuarioService;
	private List<Usuario> usuarios;
	private Usuario usuario = new Usuario();
	private String cep;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	@PostConstruct
	public void inicializar() {
		usuarios = usuarioService.selecionarTodos();
	}

	public String novo() {
		usuario = new Usuario();
		return "usuarios";
	}

	public String salvar() {
		usuarioService.salvarUsuario(usuario);
		usuario = null;
		inicializar();
		return "usuarios";
	}

	public String excluir() {
		usuarioService.excluirUsuario(usuario);
		usuarios = usuarioService.selecionarTodos();
		return "usuarios";
	}
	
	public String getCep() {
		return cep;
	}
	
}