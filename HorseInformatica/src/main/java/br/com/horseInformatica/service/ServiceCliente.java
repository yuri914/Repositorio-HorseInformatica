package br.com.horseInformatica.service;

import java.io.Serializable;
import java.util.List;

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

	public boolean autenticarClienteBanco(Cliente clienteLogin,String login, String senha) {
		boolean verifica = false;
		
		List<Cliente> listaCliente = daoCliente.autenticarCliente(clienteLogin,login,senha);

		if(listaCliente.size() >=0 ){
			clienteLogin.setDadosCliente(listaCliente);
			verifica = true;
		}
		return verifica;
	}
	
	
}
