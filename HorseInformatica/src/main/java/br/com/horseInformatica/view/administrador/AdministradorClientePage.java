package br.com.horseInformatica.view.administrador;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.service.ServiceCliente;

public class AdministradorClientePage extends AdministradorPage {

	private static final long serialVersionUID = -5732317569309869221L;
	
	@SpringBean
	private ServiceCliente serviceCliente;
	
	public AdministradorClientePage(){
		add(new AdministradorClienteForm("formAdmCliente"){

			private static final long serialVersionUID = -4530599058526072244L;

			@Override
			protected List<Cliente> buscarListaCliente() {
				return serviceCliente.findAll();
			}

			@Override
			protected List<Cliente> buscarClienteFiltro(String nomeClienteConsulta) {
				return serviceCliente.findClienteByName(nomeClienteConsulta);
			}
			
		});
	}
	
}
