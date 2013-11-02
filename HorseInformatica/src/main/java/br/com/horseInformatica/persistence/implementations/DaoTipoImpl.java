package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Tipo;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoTipo;

@Repository
public class DaoTipoImpl extends JpaGenericDao<Tipo> implements IDaoTipo{

	public DaoTipoImpl() {
		super(Tipo.class);
	}

}
