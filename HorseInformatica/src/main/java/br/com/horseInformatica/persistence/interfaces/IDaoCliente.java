package br.com.horseInformatica.persistence.interfaces;

import java.util.List;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.persistence.generics.GenericDao;

public interface IDaoCliente extends GenericDao<Cliente>{

	List<Cliente> autenticarCliente(Cliente clienteLogin,String login, String senha);

}
