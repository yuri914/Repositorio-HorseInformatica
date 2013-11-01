package br.com.horseInformatica.persistence.generics;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class JpaGenericDao <T> implements GenericDao <T> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> persistenceClass;
	
	public JpaGenericDao(Class<T> persistClass){
		this.persistenceClass = persistClass;
	}
	
	public EntityManager getEntityManager() {
		if (entityManager == null)
			System.out.println("O entityManager está nulo.");
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getPersistenceClass() {
		if(persistenceClass == null)
			System.out.println("A classe persistente não pode ser nula.");
		return persistenceClass;
	}

	public void setPersistenceClass(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
	}

	public Query createQuery(String jpql, Object...params){
		Query query = getEntityManager().createNamedQuery(jpql);
		int i = 0;
		for(Object param : params){
			query.setParameter(++i, param);
		}
		return query;
	}
	
	@Override
	@Transactional
	public void persist(Object object) {
		getEntityManager().persist(object);
	}

	@Override
	public void update(Object object) {
		getEntityManager().merge(object);
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
	}

	@Override
	public T find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String jpql = "From ?";
		Query query = createQuery(jpql, persistenceClass.getSimpleName()); 
		return query.getResultList();
	}

}
