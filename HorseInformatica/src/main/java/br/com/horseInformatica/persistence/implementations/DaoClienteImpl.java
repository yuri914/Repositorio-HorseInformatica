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
		sb.append(" where u.login = ?");
		sb.append(" and u.senha = ?");
		
		Query query = super.createQuery(sb.toString(), clienteLogin.getLogin(), clienteLogin.getSenha());
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findClienteByNameDatabase(String nomeClienteConsulta) {
		Query query = null;
		String jpql = "From Cliente c where c.nome like " + "'%" + nomeClienteConsulta + "%'";

		if (nomeClienteConsulta != null) {
			query = getEntityManager().createQuery(jpql);
		} else {
			query = getEntityManager().createQuery("From Cliente");
		}
		return query.getResultList();
	}

}
