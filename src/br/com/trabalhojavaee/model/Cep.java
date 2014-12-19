package br.com.trabalhojavaee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CEP")
public class Cep extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3064306490724801147L;

	// Cep  99999-999 
	//     regiao-sufixo

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="regiao", length=5)
	private String regiao;

	@Column(name="sufixo", length=3)
	private String sufixo;

	public Cep() {
		super();
	}

	public Cep(Long id) {
		this();
		setId(id);
	}
	
	public Cep(String regiao, String sufixo) {
		super();
		this.regiao = regiao;
		this.sufixo = sufixo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getSufixo() {
		return sufixo;
	}

	public void setSufixo(String sufixo) {
		this.sufixo = sufixo;
	}

	@Override
	public String toString() {
		
		if(regiao == null || sufixo == null)
			return "";
		
		return regiao + sufixo;
	}

}
