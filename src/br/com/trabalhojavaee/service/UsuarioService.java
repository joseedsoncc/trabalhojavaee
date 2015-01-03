package br.com.trabalhojavaee.service;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;

import br.com.trabalhojavaee.dao.CepDao;
import br.com.trabalhojavaee.dao.UsuarioDao;
import br.com.trabalhojavaee.model.Cep;
import br.com.trabalhojavaee.model.Usuario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuarioService {

	@EJB
	private UsuarioDao usuarioDao;
	@EJB
	private CepDao cepDao;

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	/** {@inheritDoc} */
	public Usuario autenticarUsuario(String cpf, String senha) throws UsuarioInvalidoException {
		Usuario usuario = usuarioDao.selecionar(cpf);
		if (usuario == null || !usuario.getSenha().equals(senha)) {
			throw new UsuarioInvalidoException();
		}
		return usuario;
	}

	/** {@inheritDoc} */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirUsuario(Usuario usuario) {
		usuarioDao.excluirUsuario(usuario);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}

	/** {@inheritDoc} */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarUsuario(Usuario usuario) {
		cepDao.salvarCep(usuario.getCep());
		Cep cepSalvo = cepDao.selecionar(usuario.getCep());
		usuario.setCep(cepSalvo);
		usuarioDao.salvarUsuario(usuario);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.xhtml");
	}

	/** {@inheritDoc} */
	public Usuario selecionar(Usuario usuario) {
		return usuarioDao.selecionar(usuario);
	}

	/** {@inheritDoc} */
	public List<Usuario> selecionarTodos() {
		return usuarioDao.selecionarTodos();
	}

}
