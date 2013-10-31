package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Contato;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoContato;

@Repository
public class DaoContatoImpl extends JpaGenericDao<Contato> implements IDaoContato {

	public DaoContatoImpl() {
		super(Contato.class);
		
	}

}
