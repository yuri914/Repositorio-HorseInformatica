package br.com.horseInformatica.view.administrador;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import br.com.horseInformatica.model.Cliente;

public class AdministradorClientePanel extends Panel {

	private static final long serialVersionUID = -8023850191678212745L;

	public AdministradorClientePanel(String id) {
		super(id);
		
	}

	public void getGridCliente(List<Cliente> listaClientes){
		DataView<Cliente> repetidor = new DataView<Cliente>("clientes", new ListDataProvider<Cliente>(listaClientes)){

			private static final long serialVersionUID = 5863600565352946239L;

			@Override
			protected void populateItem(Item<Cliente> item) {
				Cliente clienteAtual = item.getModelObject();
				item.add(new Label("nome", clienteAtual.getNome()));
				item.add(new Label("cpf", clienteAtual.getCpf()));
				item.add(new Label("perfil", clienteAtual.getPerfil().getNome()));
				item.add(new Label("telefone", clienteAtual.getContato().getTelefone()));
			}
		};
		add(new PagingNavigator("paginacao", repetidor));
		add(repetidor);
	}
	
}
