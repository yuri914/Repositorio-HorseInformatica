package br.com.horseInformatica.persistence.implementations;

import br.com.horseInformatica.model.Contato;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoContato;

public class DaoContatoImpl extends JpaGenericDao<Contato> implements IDaoContato {

	public DaoContatoImpl() {
		super(Contato.class);
		
	}

}
