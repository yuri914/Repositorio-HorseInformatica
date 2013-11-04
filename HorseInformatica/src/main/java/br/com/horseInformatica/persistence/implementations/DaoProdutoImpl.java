package br.com.horseInformatica.persistence.implementations;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoProduto;

@Repository
public class DaoProdutoImpl extends JpaGenericDao<Produto> implements IDaoProduto {

	public DaoProdutoImpl() {
		super(Produto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarProdutosTipoBanco(Integer codigoTipo) {
		String jpql = "FROM Produto p where p.tipo.id = ?";
		
		Query query = super.createQuery(jpql,codigoTipo);

		return query.getResultList();
	}
}