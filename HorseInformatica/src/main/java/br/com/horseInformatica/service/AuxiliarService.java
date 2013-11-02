package br.com.horseInformatica.service;

import java.io.Serializable;

import org.springframework.web.client.RestTemplate;

import br.com.horseInformatica.to.EnderecoTO;

public class AuxiliarService implements Serializable {

	private static final long serialVersionUID = -6295615332105282113L;

	public static EnderecoTO getEnderecoWebService(Integer cep){
		RestTemplate rest = new RestTemplate();
		return rest.getForObject("http://cep.paicon.com.br/json/" + cep, EnderecoTO.class);
	}
}
