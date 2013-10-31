package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoProduto;

@Repository
public class DaoProdutoImpl extends JpaGenericDao<Produto> implements IDaoProduto {

	public DaoProdutoImpl() {
		super(Produto.class);
	}

}
