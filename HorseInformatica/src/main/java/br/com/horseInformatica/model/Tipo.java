package br.com.horseInformatica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_produto")
public class Tipo implements Serializable {

	private static final long serialVersionUID = -1560341941639172095L;

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "nome")
	private String nome;
	
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
	
	
}
