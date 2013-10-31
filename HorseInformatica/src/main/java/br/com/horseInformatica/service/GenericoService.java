package br.com.horseInformatica.service;

import java.util.List;

import br.com.horseInformatica.persistence.generics.GenericDao;

public abstract class GenericoService<T> {

	protected abstract GenericDao<T> getDao();

	public void persist(T object) {

	}

	public void update(T object) {

	}

	public void delete(T object) {

	}

	public T find(Integer id) {
		return null;

	}

	public List<T> findAll() {
		return null;
	}

}
