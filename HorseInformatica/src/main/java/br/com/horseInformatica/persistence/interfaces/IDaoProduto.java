package br.com.horseInformatica.persistence.interfaces;

import java.util.List;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.GenericDao;

public interface IDaoProduto extends GenericDao<Produto>{

	List<Produto> listarProdutosTipoBanco(Integer codigoTipo);
}
