package br.com.trabalhojavaee.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;

@SessionScoped
@ManagedBean(name = "guessBean")
public class GuessNumberBean {

	private Integer numero;

	private Integer palpite;

	private Integer tentativas;

	private String mensagem;

	private UIInput palpiteInput;

	public UIInput getPalpiteInput() {
		return palpiteInput;
	}

	public void setPalpiteInput(UIInput palpiteInput) {
		this.palpiteInput = palpiteInput;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getPalpite() {
		return palpite;
	}

	public void setPalpite(Integer palpite) {
		this.palpite = palpite;
	}

	public Integer getTentativas() {
		return tentativas;
	}

	public void setTentativas(Integer tentativas) {
		this.tentativas = tentativas;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String init() {
		numero = (int) (1 + (Math.random() * 100));
		palpite = null;
		tentativas = 0;
		mensagem = "page.guess.label.branco";
		if (palpiteInput != null) {
			palpiteInput.setRendered(true);
		}
		return "guess";
	}

	public String guess() {
		if (palpite.equals(numero)) {
			mensagem = "page.guess.acerto";
			palpiteInput.setRendered(false);
		} else if (numero.compareTo(palpite) < 0) {
			mensagem = "page.guess.menor";
		} else {
			mensagem = "page.guess.maior";
		}
		tentativas++;
		return "guess";
	}
	
	public void onChange(ValueChangeEvent event) {
	    if (event.getNewValue() == null) {
	        mensagem = "page.guess.digiteNumero";
	    } else { 
	        mensagem = "page.guess.label.branco";
	   } 
	}
}