package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Tipo;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoTipo;

@Service("serviceTipo")
public class ServiceTipo extends GenericoService<Tipo> implements Serializable {

	private static final long serialVersionUID = -1597666664216383402L;

	@Autowired
	private IDaoTipo daoTipo;
	
	@Override
	protected GenericDao<Tipo> getDao() {

		return daoTipo;
	}

}
