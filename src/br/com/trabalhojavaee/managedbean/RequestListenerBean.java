package br.com.trabalhojavaee.managedbean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.trabalhojavaee.listeners.RequestListener;

@ManagedBean(name = "requestListenerBean")
@ApplicationScoped
public class RequestListenerBean {
	
	private Map<String, Integer> lista = new HashMap<String, Integer>();
	
	public Map<String, Integer> getLista() {
		System.out.println(lista);
		return lista;
	}
	
	public RequestListenerBean() {
		this.lista = RequestListener.getAcessosPagina();
	}
}
