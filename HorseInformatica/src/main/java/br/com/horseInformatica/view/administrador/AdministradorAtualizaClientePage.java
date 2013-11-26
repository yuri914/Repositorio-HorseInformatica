package br.com.horseInformatica.view.administrador;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.service.ServiceCliente;

public class AdministradorAtualizaClientePage extends WebPage {

	private static final long serialVersionUID = 3494068590909713216L;
	@SpringBean
	private ServiceCliente serviceCliente;
	
	public AdministradorAtualizaClientePage(Cliente cliente) {
		add(new AdmistradorAtualizaClienteForm("formularioAtualizar", cliente){

			private static final long serialVersionUID = 8512385576069462696L;

			@Override
			protected void atualizarCliente(Cliente cliente) {
				serviceCliente.atualizar(cliente);
			}
			
		});
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
}
