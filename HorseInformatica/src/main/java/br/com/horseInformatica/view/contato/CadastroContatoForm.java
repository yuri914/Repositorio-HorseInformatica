package br.com.horseInformatica.view.contato;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.model.Contato;
import br.com.horseInformatica.model.Endereco;
import br.com.horseInformatica.model.Perfil;
import br.com.horseInformatica.service.AuxiliarService;
import br.com.horseInformatica.to.EnderecoTO;
import br.com.horseInformatica.util.enumerations.EnumDDD;
import br.com.horseInformatica.view.base.BasePage;

public abstract class CadastroContatoForm extends Form<Contato> {

	private static final long serialVersionUID = -6472581812156846712L;

	private FeedbackPanel feedback;
	private TextField<String> email;
	private TextField<String> telefone;
	private DropDownChoice<EnumDDD> ddd;
	private Contato contato;

	private TextField<Integer> cep;

	private TextField<String> logradouro;

	private TextField<String> bairro;

	private TextField<String> cidade;

	private TextField<String> estado;

	private AjaxButton btConfirmar;

	public CadastroContatoForm(String id, final Cliente cliente) {
		super(id);

		feedback = new FeedbackPanel("mensagem");
		feedback.setOutputMarkupId(true);
		add(feedback);

		email = new TextField<String>("email");
		email.setModel(new PropertyModel<String>(getContato(), "email"));
		add(email);

		telefone = new TextField<String>("telefone");
		telefone.setModel(new PropertyModel<String>(getContato(), "telefone"));
		add(telefone);

		ddd = new DropDownChoice<EnumDDD>("ddd");
		ddd.setChoices(Arrays.asList(EnumDDD.values()));
		ddd.setModel(new PropertyModel<EnumDDD>(getContato(), "ddd"));
		ddd.setChoiceRenderer(new ChoiceRenderer<EnumDDD>("codigo"));
		add(ddd);

		cep = new TextField<Integer>("cep");
		cep.setModel(new PropertyModel<Integer>(getEndereco(), "cep"));
		cep.add(new AjaxFormComponentUpdatingBehavior("onblur") {

			private static final long serialVersionUID = -1358699233483750529L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				EnderecoTO enderecoCep = AuxiliarService.getEnderecoWebService(cep.getModelObject());
				if(enderecoCep != null){
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
		logradouro.setModel(new PropertyModel<String>(getEndereco(), "logradouro"));
		add(logradouro);

		bairro = new TextField<String>("bairro");
		bairro.setModel(new PropertyModel<String>(getEndereco(), "bairro"));
		add(bairro);

		cidade = new TextField<String>("cidade");
		cidade.setModel(new PropertyModel<String>(getEndereco(), "cidade"));
		add(cidade);

		estado = new TextField<String>("estado");
		estado.setModel(new PropertyModel<String>(getEndereco(), "estado"));
		add(estado);

		btConfirmar = new AjaxButton("confirmar") {

			private static final long serialVersionUID = -4309959029136917439L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				getEndereco().setLogradouro(logradouro.getModelObject());
				getEndereco().setBairro(bairro.getModelObject());
				getEndereco().setCidade(cidade.getModelObject());
				getEndereco().setEstado(estado.getModelObject());
				getContato().setEndereco(getEndereco());
				cliente.setContato(getContato());
				salvarCliente(cliente);
				target.appendJavaScript("alert ('Cadastro realizado com sucesso.')");
				setResponsePage(BasePage.class);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				System.out.println("Erro");
				target.add(feedback);
			}

		};
		add(btConfirmar);
	}

	protected abstract void salvarContato(Contato contato);

	protected abstract void salvarEndereco(Endereco endereco);

	protected abstract void salvarPerfil(Perfil perfil);

	protected abstract void salvarCliente(Cliente cliente);

	Endereco endereco;

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public Contato getContato() {
		if (contato == null) {
			contato = new Contato();
		}
		return contato;
	}

	
	
}
