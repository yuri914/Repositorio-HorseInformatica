package br.com.horseInformatica.view.administrador;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import br.com.horseInformatica.model.Cliente;

public abstract class AdministradorClienteForm extends Form<Cliente> {

	private static final long serialVersionUID = -5228865222034777465L;

	private AdministradorClientePanel panelCliente;
	private FeedbackPanel feedback;

	private AjaxButton btConsultar;

	private TextField<String> nomeCliente;

	
	public AdministradorClienteForm(String id) {
		super(id);
		
		feedback = new FeedbackPanel("mensages");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		nomeCliente = new TextField<String>("nomeCliente");
		nomeCliente.setModel(new Model<String>());
		add(nomeCliente);
		
		panelCliente = new AdministradorClientePanel("panelClientes");
		panelCliente.getGridCliente(buscarListaCliente());
		panelCliente.setOutputMarkupId(true);
		add(panelCliente);
		
		btConsultar = new AjaxButton("consultar"){
			
			private static final long serialVersionUID = -6834425602136707880L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				List<Cliente> listaClienteFiltrado = buscarClienteFiltro(nomeCliente.getModelObject());
				if (listaClienteFiltrado.size() != 0){
					panelCliente.getGridCliente(listaClienteFiltrado);
				} else {
					error("Nenhum cliente encontrado com o nome informado.");
				}
				target.add(panelCliente, feedback);
			}
		};
		add(btConsultar);
	}


	protected abstract List<Cliente> buscarClienteFiltro(String nomeClienteConsulta);

	protected abstract List<Cliente> buscarListaCliente();

}
