package br.com.trabalhojavaee.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.com.trabalhojavaee.model.Usuario;

/**
 * DAO da entidade Usuario.
 * 
 * @author Paulo
 */
@Stateless
public class UsuarioDao extends GenericDao<Usuario> {

	public void salvarUsuario(Usuario usuario) {
		super.salvarEntidade(usuario);
	}
	
	public List<Usuario> selecionarTodos() {
		return super.selecionarTodos();
	}
	
	public Usuario selecionar(Usuario usuario) {
		return super.selecionarEntidade(usuario);
	}
	
	public void excluirUsuario(Usuario usuario) {
		super.excluirEntidade(usuario);
	}

	public Usuario selecionar(String cpf) {
		List<Usuario> usuarios = selecionarTodos();
		for (Usuario usuarioLista : usuarios) {
			if (usuarioLista.getCpf().equals(cpf)) {
				return usuarioLista;
			}
		}

		return null;
	}

	@Override
	protected Class<Usuario> getClasseEntidade() {
		return Usuario.class;
	}
}
