package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Venda;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoVenda;

@Service("serviceVenda")
public class ServiceVenda  extends GenericoService<Venda>implements Serializable{

	private static final long serialVersionUID = -2296844001721380072L;

	@Autowired
	private IDaoVenda daoVenda;
	@Override
	protected GenericDao<Venda> getDao() {
		
		return daoVenda;
	}

}
