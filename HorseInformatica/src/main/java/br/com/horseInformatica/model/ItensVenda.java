package br.com.horseInformatica.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_itens_venda")
public class ItensVenda implements Serializable{

	private static final long serialVersionUID = 4118158237574641361L;
	
	
	private Produto produto;
	
	private Venda venda;

}
