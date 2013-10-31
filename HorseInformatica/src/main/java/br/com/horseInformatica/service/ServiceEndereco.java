package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Endereco;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoEndereco;

@Service("serviceEndereco")
public class ServiceEndereco extends GenericoService<Endereco> implements Serializable{

	private static final long serialVersionUID = 5746677632755393675L;

	private IDaoEndereco daoEndereco;
	@Override
	protected GenericDao<Endereco> getDao() {
		
		return daoEndereco;
	}

}
