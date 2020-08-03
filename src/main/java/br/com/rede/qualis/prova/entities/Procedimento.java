package br.com.rede.qualis.prova.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Procedimento implements Serializable {

	private static final long serialVersionUID = -2276891733404082425L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private int procedimento;

	@Column(nullable = false)
	private int idade;

	@Column(nullable = false)
	private char sexo;

	@Column(nullable = false)
	private Boolean permitido;

	public Procedimento() {
	}

	public Procedimento(int procedimento, int idade, char sexo, Boolean permitido) {
		super();
		this.procedimento = procedimento;
		this.idade = idade;
		this.sexo = sexo;
		this.permitido = permitido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(int procedimento) {
		this.procedimento = procedimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Boolean getPermitido() {
		return permitido;
	}

	public void setPermitido(Boolean permitido) {
		this.permitido = permitido;
	}

	@Override
	public String toString() {
		return String.format("Procedimento [id=%s, procedimetno=%s, idade=%s, sexo=%s, permitido=%s]", id, procedimento,
				idade, sexo, permitido);
	}

}
