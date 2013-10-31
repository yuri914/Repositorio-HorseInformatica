package br.com.horseInformatica.persistence.implementations;

import br.com.horseInformatica.model.Perfil;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoPerfil;

public class DaoPerfilImpl extends JpaGenericDao<Perfil> implements IDaoPerfil {

	public DaoPerfilImpl() {
		super(Perfil.class);
	}

}
