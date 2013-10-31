package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Perfil;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoPerfil;

@Repository
public class DaoPerfilImpl extends JpaGenericDao<Perfil> implements IDaoPerfil {

	public DaoPerfilImpl() {
		super(Perfil.class);
	}

}
