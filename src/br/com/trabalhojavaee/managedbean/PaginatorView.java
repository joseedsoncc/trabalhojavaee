package br.com.trabalhojavaee.managedbean;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.trabalhojavaee.model.Usuario;
 
@ManagedBean(name="dtPaginatorView")
@RequestScoped
public class PaginatorView implements Serializable {
     
	private static final long serialVersionUID = 9099949021576364923L;

	public List<Usuario> usuarios;
     
    @ManagedProperty("#{usuarioMB}")
    public UsuarioMB usuarioMB;
 
    @PostConstruct
    public void init() {
    	usuarios = usuarioMB.getUsuarios();
    }
     
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioMB getUsuarioMB() {
		return usuarioMB;
	}

	public void setUsuarioMB(UsuarioMB usuarioMB) {
		this.usuarioMB = usuarioMB;
	}

}