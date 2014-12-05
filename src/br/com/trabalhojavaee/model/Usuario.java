package br.com.trabalhojavaee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USUARIO")
public class Usuario extends BaseModel implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -3064306490724801147L;

    @Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="nome")
	private String nome;

	@Column(name="login")
	private String login;

	@Column(name="senha")
	private String senha;
	
	@Column(name="cpf")
	private String cpf; 
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataNascimento")
	private Date dataNascimento;
	
	@Email
	@Column(name="email")
	private String email;
	
	/**
	 * Construtor vazio.
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * Construtor Id.
	 * @param id
	 */
    public Usuario(Long id) {
        this();
        setId(id);
    }

    /**
     * Construtor completo.
     * @param nome
     * @param login
     * @param senha
     * @param dataNascimento
     * @param email
     */
	public Usuario(String nome, String login, String senha,
			Date dataNascimento, String email,String cpf) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 public void validate() {
	        if(nome == null || login == null){
	            throw new IllegalArgumentException("Nome e Login devem ser preenchidos.");
	        }
	    }

}
