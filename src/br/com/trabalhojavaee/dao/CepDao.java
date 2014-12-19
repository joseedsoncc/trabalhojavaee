package br.com.trabalhojavaee.dao;

import javax.ejb.Stateless;

import br.com.trabalhojavaee.model.Cep;

@Stateless
public class CepDao extends GenericDao<Cep> {

	public void salvarCep(Cep cep) {
		if(cep.getId() != null) {
			super.atualizarEntidade(cep);
		} else {
			super.inserirEntidade(cep);
		}
	}
		
	public Cep selecionar(Cep cep) {
		return super.selecionarEntidade(cep);
	}
	
	@Override
	protected Class<Cep> getClasseEntidade() {
		return Cep.class;
	}
	
}
