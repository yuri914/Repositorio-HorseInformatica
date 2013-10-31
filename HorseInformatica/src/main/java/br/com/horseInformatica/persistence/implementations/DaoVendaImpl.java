package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Venda;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoVenda;

@Repository
public class DaoVendaImpl extends JpaGenericDao<Venda>implements IDaoVenda {

	public DaoVendaImpl() {
		super(Venda.class);
		
	}

}
