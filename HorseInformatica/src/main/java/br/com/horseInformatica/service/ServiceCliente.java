package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoCliente;

@Service("serviceCliente")
public class ServiceCliente extends GenericoService<Cliente> implements Serializable{

	private static final long serialVersionUID = 285941439575888475L;

	@Autowired
	private IDaoCliente daoCliente;

	@Override
	protected GenericDao<Cliente> getDao() {
		
		return daoCliente;
	}

	public Cliente autenticarClienteBanco(Cliente clienteLogin) {
		return null;
	}
	
	
}
