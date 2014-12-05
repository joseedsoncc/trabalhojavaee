package br.com.trabalhojavaee.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.trabalhojavaee.model.Usuario;
import br.com.trabalhojavaee.service.UsuarioService;

@ManagedBean (name="usuarioMB")
@RequestScoped
public class UsuarioMB {
	/** 
	 * Injecao para camada de negocio 
	 */
	@EJB
	private UsuarioService usuarioService;

	private List<Usuario> usuarios;
	
	private Usuario usuario = new Usuario();

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
		return "usuariosEditar";
	}

	public String salvar() {
		usuarioService.salvarUsuario(usuario);
		inicializar();
		return "usuarios";
	}

	public String excluir() {
		usuarioService.excluirUsuario(usuario);
		usuarios = usuarioService.selecionarTodos();
		return "usuarios";
	}
}