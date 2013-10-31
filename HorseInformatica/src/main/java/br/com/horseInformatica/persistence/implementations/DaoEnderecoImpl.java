package br.com.horseInformatica.persistence.implementations;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Endereco;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoEndereco;

@Repository
public class DaoEnderecoImpl extends JpaGenericDao<Endereco> implements IDaoEndereco {

	public DaoEnderecoImpl() {
		super(Endereco.class);
	
	}

}
