package br.com.horseInformatica.persistence.implementations;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoCliente;

public class DaoClienteImpl extends JpaGenericDao<Cliente> implements IDaoCliente {

	public DaoClienteImpl() {
		super(Cliente.class);
	
	}

}
