package br.com.horseInformatica.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

public class ItensVenda implements Serializable{

	private static final long serialVersionUID = 4118158237574641361L;
	
	private Integer id;
	private Produto produto;
	private Venda venda;

}
