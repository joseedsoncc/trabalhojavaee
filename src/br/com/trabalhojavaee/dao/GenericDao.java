package br.com.trabalhojavaee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.trabalhojavaee.model.BaseModel;

/**
 * Dao generico do projeto que implementa os metodos de persistencia.
 */
public abstract class GenericDao<T extends BaseModel> {

	@PersistenceContext(unitName = "jsf_ejb_jpa-unit")
	protected EntityManager entityManager;

	protected abstract Class<T> getClasseEntidade();

	
	protected void inserirEntidade(T entidade) {
		entityManager.persist(entidade);
	}

	
	protected void excluirEntidade(T entidade) {
		T entity = selecionarEntidade(entidade);
		entityManager.remove(entity);
	}
	
	protected void salvarEntidade(T entidade) {
		if (entidade.getId() == null) {
			inserirEntidade(entidade);
		} else {
			atualizarEntidade(entidade);
		}
	}

	protected void atualizarEntidade(T entidade) {
		entityManager.merge(entidade);
	}


	
	protected T selecionarEntidade(final T entidade) {
		return entityManager.find(getClasseEntidade(), entidade.getId());
	}


	@SuppressWarnings("unchecked")
	protected List<T> selecionarTodos() {
		Query query = entityManager.createQuery("Select e from "
				+ getClasseEntidade().getName() + " e ");
		return query.getResultList();
	}


}