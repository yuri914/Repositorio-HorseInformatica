package br.com.horseInformatica.view.administrador;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.service.AuxiliarService;
import br.com.horseInformatica.to.EnderecoTO;
import br.com.horseInformatica.util.enumerations.EnumSexo;

public abstract class AdmistradorAtualizaClienteForm extends Form<Cliente> {

	private static final long serialVersionUID = 2535896341184251584L;

	
	
	private FeedbackPanel feedback;
	
	//Dados Pessoais
	private TextField<String> nome;
	private RadioChoice<EnumSexo> genero;
	private DateTextField nascimento;
	private TextField<String> cpf;
	private TextField<String> rg;

	//Contato
	private TextField<String> email;
	private TextField<String> telefone;
	private TextField<Integer> ddd;
	private TextField<Integer> cep;
	private TextField<String> logradouro;
	private TextField<String> bairro;
	private TextField<String> cidade;
	private TextField<String> estado;



	private AjaxButton btConfirmar;
	
	public AdmistradorAtualizaClienteForm(String id, final Cliente cliente) {
		super(id);
		
	
		feedback = new FeedbackPanel("mensagem");
		feedback.setOutputMarkupPlaceholderTag(true);
		add(feedback);

		
		nome = new TextField<String>("nome");
		nome.setModel(new PropertyModel<String>(cliente, "nome"));
		add(nome);

		cpf = new TextField<String>("cpf");
		cpf.setModel(new PropertyModel<String>(cliente, "cpf"));
		add(cpf);

		rg = new TextField<String>("rg");
		rg.setModel(new PropertyModel<String>(cliente, "rg"));
		add(rg);

		nascimento = new DateTextField("dataNascimento", new PropertyModel<Date>(cliente, "dataNascimento"), new StyleDateConverter("S-", true));

		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		nascimento.add(datePicker);
		add(nascimento);

		genero = new RadioChoice<EnumSexo>("genero");
		genero.setChoices(Arrays.asList(EnumSexo.values()));
		genero.setChoiceRenderer(new ChoiceRenderer<EnumSexo>("descricao"));
		genero.setModel(new PropertyModel<EnumSexo>(cliente, "sexo"));
		genero.setSuffix("  ");
		add(genero);

		email = new TextField<String>("email");
		email.setModel(new PropertyModel<String>(cliente.getContato(), "email"));
		add(email);

		telefone = new TextField<String>("telefone");
		telefone.setModel(new PropertyModel<String>(cliente.getContato(), "telefone"));
		add(telefone);

		/*ddd = new DropDownChoice<Integer>("ddd");
		List<Integer> listaDdd = new ArrayList<Integer>(Arrays.asList(21, 11, 71, 85, 51, 31));
		ddd.setChoices(listaDdd);
		ddd.setModel(new PropertyModel<Integer>(cliente.getContato(), "ddd"));*/
		
		ddd = new TextField<Integer>("ddd");
		ddd.setModel(new PropertyModel<Integer>(cliente.getContato(), "ddd"));
		add(ddd);

		cep = new TextField<Integer>("cep");
		cep.setModel(new PropertyModel<Integer>(cliente.getContato().getEndereco(), "cep"));
		cep.add(new AjaxFormComponentUpdatingBehavior("onblur") {

			private static final long serialVersionUID = -1358699233483750529L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				EnderecoTO enderecoCep = AuxiliarService.getEnderecoWebService(cep.getModelObject());
				if (enderecoCep != null) {
					logradouro.setModel(Model.of(enderecoCep.getLogradouro()));
					bairro.setModel(Model.of(enderecoCep.getBairro()));
					estado.setModel(Model.of(enderecoCep.getEstado()));
					cidade.setModel(Model.of(enderecoCep.getCidade()));
				} else {
					error("Cep não encontrado.");
				}
				target.add(logradouro, bairro, estado, cidade, feedback);
			}
		});
		add(cep);

		logradouro = new TextField<String>("logradouro");
		logradouro.setModel(new PropertyModel<String>(cliente.getContato().getEndereco(), "logradouro"));
		add(logradouro);

		bairro = new TextField<String>("bairro");
		bairro.setModel(new PropertyModel<String>(cliente.getContato().getEndereco(), "bairro"));
		add(bairro);

		cidade = new TextField<String>("cidade");
		cidade.setModel(new PropertyModel<String>(cliente.getContato().getEndereco(), "cidade"));
		add(cidade);

		estado = new TextField<String>("estado");
		estado.setModel(new PropertyModel<String>(cliente.getContato().getEndereco(), "estado"));
		add(estado);
		
		
		btConfirmar = new AjaxButton("confirmar") {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				 atualizarCliente(cliente);
				 String menssagem = "Cliente atualizado com sucesso !";
				 setResponsePage(new AdministradorClientePage());
			}

		
		};
		add(btConfirmar);

	}

	protected abstract void atualizarCliente(Cliente cliente);
}
