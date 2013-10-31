package br.com.horseInformatica.persistence.implementations;

import br.com.horseInformatica.model.Venda;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoVenda;

public class DaoVendaImpl extends JpaGenericDao<Venda>implements IDaoVenda {

	public DaoVendaImpl() {
		super(Venda.class);
		
	}

}
