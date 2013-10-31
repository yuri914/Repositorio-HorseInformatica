package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoCliente;

@Repository
public class DaoClienteImpl extends JpaGenericDao<Cliente> implements IDaoCliente {

	public DaoClienteImpl() {
		super(Cliente.class);
	
	}

}
