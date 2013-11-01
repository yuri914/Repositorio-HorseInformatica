package br.com.horseInformatica.view.contato;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.model.Contato;
import br.com.horseInformatica.model.Endereco;
import br.com.horseInformatica.model.Perfil;
import br.com.horseInformatica.service.ServiceCliente;
import br.com.horseInformatica.service.ServiceContato;
import br.com.horseInformatica.service.ServiceEndereco;
import br.com.horseInformatica.service.ServicePerfil;

public class CadastroContatoPage extends WebPage {

	private static final long serialVersionUID = -3061101273846571187L;
	
	@SpringBean
	private ServiceCliente serviceCliente;
	
	@SpringBean
	private ServiceContato serviceContato;
	
	@SpringBean
	private ServicePerfil servicePerfil;
	
	@SpringBean
	private ServiceEndereco serviceEndereco;
	
	public CadastroContatoPage(Cliente clienteCadastro){
		add(new CadastroContatoForm("formularioContato", clienteCadastro){

			private static final long serialVersionUID = -3368329823926836178L;

			@Override
			protected void salvarCliente(Cliente cliente) {
				serviceCliente.persist(cliente);
			}

			@Override
			protected void salvarContato(Contato contato) {
				serviceContato.persist(contato);
			}

			@Override
			protected void salvarPerfil(Perfil perfil) {
				servicePerfil.persist(perfil);
			}

			@Override
			protected void salvarEndereco(Endereco endereco) {
				
				serviceEndereco.persist(endereco);
			}
			
		});
		
	
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
}
