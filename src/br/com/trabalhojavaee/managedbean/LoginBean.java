package br.com.trabalhojavaee.managedbean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.trabalhojavaee.model.Usuario;
import br.com.trabalhojavaee.service.UsuarioInvalidoException;
import br.com.trabalhojavaee.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginBean {
	/** Referencia para a camada de regras de negocio */
	@EJB
	private UsuarioService usuarioService;
	/** Usuario autenticado na aplicacao */
	
	private Usuario usuarioAutenticado;
	private String cpf; private String senha;
	/** Gerar get's e set's */
	public String autenticar() {
		try {
			usuarioAutenticado = usuarioService.autenticarUsuario(cpf, senha);
			return "index?faces-redirect=true";
		} catch (UsuarioInvalidoException e) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail("Usuário ou senha inválidos!");
			FacesContext.getCurrentInstance().addMessage("loginForm", message);
			return null;
		} finally {
			cpf = null; senha = null;
		}
	}
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}