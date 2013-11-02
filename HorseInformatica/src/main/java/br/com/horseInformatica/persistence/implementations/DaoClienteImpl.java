package br.com.horseInformatica.persistence.implementations;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoCliente;

@Repository
public class DaoClienteImpl extends JpaGenericDao<Cliente> implements IDaoCliente {

	public DaoClienteImpl() {
		super(Cliente.class);

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> autenticarCliente(Cliente clienteLogin) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("From Cliente u");
		sb.append(" WHERE 1=1");
		if(clienteLogin.getLogin() != "" && clienteLogin.getSenha() != ""){
			sb.append(" and u.login = '"+ clienteLogin.getLogin() +"'");
			sb.append(" and u.senha = '"+ clienteLogin.getSenha() +"'");
		}
		return getEntityManager().createQuery(sb.toString()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findClienteByNameDatabase(String nomeClienteConsulta){
		Query query = null;
		String jpql = "From Cliente c where c.nome like ?";
		
		if (nomeClienteConsulta != null){
			query = super.createQuery(jpql, "%'" + nomeClienteConsulta + "'%");
		}
		return query.getResultList();
	}
	
}
