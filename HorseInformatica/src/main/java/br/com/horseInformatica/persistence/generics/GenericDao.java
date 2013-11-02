package br.com.horseInformatica.persistence.generics;

import java.util.List;

import br.com.horseInformatica.model.Cliente;

public interface GenericDao <T> {

	public void persist(T object);
	public void update(T object);
	public void delete(T object);
	public T find(Integer id);
	public List<T> findAll();
	
}
