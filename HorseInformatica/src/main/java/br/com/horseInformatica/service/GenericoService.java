package br.com.horseInformatica.service;

import java.util.List;

import br.com.horseInformatica.persistence.generics.GenericDao;

public abstract class GenericoService<T> {

	protected abstract GenericDao<T> getDao();

	public void persist(T object) {
		getDao().persist(object);
	}

	public void update(T object) {
		getDao().update(object);
	}

	public void delete(Integer id) {
		getDao().delete(id);
	}

	public T find(Integer id) {
		return getDao().find(id);

	}

	public List<T> findAll() {
		return getDao().findAll();
	}

}
