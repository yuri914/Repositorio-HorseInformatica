package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Perfil;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoPerfil;

@Service("servicePerfil")
public class ServicePerfil extends GenericoService<Perfil> implements Serializable{


	private static final long serialVersionUID = -233428725293320192L;

	@Autowired
	private IDaoPerfil daoPerfil;
	
	@Override
	protected GenericDao<Perfil> getDao() {
		
		return daoPerfil;
	}

}
