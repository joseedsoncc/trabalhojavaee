package br.com.trabalhojavaee.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.trabalhojavaee.dao.UsuarioDao;
import br.com.trabalhojavaee.model.Usuario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuarioService {

	@EJB
	private UsuarioDao usuarioDao;

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	/** {@inheritDoc} */
	public Usuario autenticarUsuario(String cpf, String senha)
			throws UsuarioInvalidoException {
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
	}

	/** {@inheritDoc} */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarUsuario(Usuario usuario) {
		usuarioDao.salvarUsuario(usuario);
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
