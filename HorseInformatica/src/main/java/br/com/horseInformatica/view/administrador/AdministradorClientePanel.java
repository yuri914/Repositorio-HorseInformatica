package br.com.horseInformatica.view.administrador;

import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import br.com.horseInformatica.model.Cliente;

public abstract class AdministradorClientePanel extends Panel
{

   private static final long serialVersionUID = -8023850191678212745L;

	private List<Cliente> listaItensExclusao;
	
	public AdministradorClientePanel(String id) {
		super(id);
		
	}

   public void setGridCliente(List<Cliente> listaClientes)
   {
      DataView<Cliente> repetidor =
         new DataView<Cliente>("clientes", new ListDataProvider<Cliente>(listaClientes), 10)
         {

            private static final long serialVersionUID = 5863600565352946239L;
            private AjaxButton btExcluir;
			private AjaxButton btDetalhes;

			@Override
			protected void populateItem(Item<Cliente> item) {
				final Cliente clienteAtual = item.getModelObject();
				item.add(new Label("nome", clienteAtual.getNome()));
				item.add(new Label("cpf", clienteAtual.getCpf()));
				item.add(new Label("perfil", clienteAtual.getPerfil().getNome()));
				item.add(criarCheckBox(item));
				item.add(new Label("telefone", clienteAtual.getContato().getTelefone()));
				
				btExcluir = new AjaxButton("excluir") {
					
					private static final long serialVersionUID = -5362341631320267779L;

					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						excluirCliente(clienteAtual, target);
					}
				};
				item.add(btExcluir);
				
				btDetalhes = new AjaxButton("detalhes"){

					private static final long serialVersionUID = -2410764846228280739L;
					
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						exibirDetalhesCliente(clienteAtual, target);
					}
					
				};
				item.add(btDetalhes);
			}
		};
		addOrReplace(new PagingNavigator("paginacao", repetidor));
		addOrReplace(repetidor);
	}
	
	protected CheckBox criarCheckBox(final Item<Cliente> item) {
		CheckBox checkBox = new CheckBox("selecionar", new PropertyModel<Boolean>(item.getModelObject(), "checkSelecionada"));
		checkBox.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			
			private static final long serialVersionUID = -7706675257121904200L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				listaItensExclusao.add(item.getModelObject());
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e) {
				// TODO Auto-generated method stub
			}
		});
		return checkBox;
	}

	public List<Cliente> getListaItensExclusao() {
		return listaItensExclusao;
	}

	protected abstract void excluirCliente(Cliente clienteAtual, AjaxRequestTarget target);
	protected abstract void exibirDetalhesCliente(Cliente clienteAtual, AjaxRequestTarget target);
}
