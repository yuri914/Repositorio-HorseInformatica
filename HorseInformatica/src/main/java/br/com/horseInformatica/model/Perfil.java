package br.com.horseInformatica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_perfil")
public class Perfil implements Serializable{

	private static final long serialVersionUID = -4158975603699539658L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descontoProduto")
	private Double descontoProduto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getDescontoProduto() {
		return descontoProduto;
	}

	public void setDescontoProduto(Double descontoProduto) {
		this.descontoProduto = descontoProduto;
	}
	
	
}
