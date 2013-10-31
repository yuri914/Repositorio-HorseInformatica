package br.com.horseInformatica.view;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.service.ServiceCliente;

public class HomePage extends WebPage {

	private static final long serialVersionUID = 5684923330872633189L;

	@SpringBean
	private ServiceCliente serviceCliente;
	
	public HomePage() {
		add(new HomeForm("formularioPrincipal"){

			private static final long serialVersionUID = 3838202755006038831L;

			protected void salvarCliente(Cliente cliente) {
				serviceCliente.persist(cliente);
			}

			protected Cliente autenticarCliente(Cliente clienteLogin) {
				return serviceCliente.autenticarClienteBanco(clienteLogin);
			}
		});
    }
	

	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
	
}
