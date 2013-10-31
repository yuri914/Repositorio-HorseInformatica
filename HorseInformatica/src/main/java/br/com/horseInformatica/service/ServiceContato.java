package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Contato;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoContato;

@Service("serviceContato")
public class ServiceContato extends GenericoService<Contato> implements Serializable{

	private static final long serialVersionUID = -4185069528741118326L;

	@Autowired
	private IDaoContato daoContato;

	@Override
	protected GenericDao<Contato> getDao() {
		
		return daoContato;
	}
}
