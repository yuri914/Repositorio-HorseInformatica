package br.com.horseInformatica.view.contato;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.model.Contato;
import br.com.horseInformatica.model.Endereco;
import br.com.horseInformatica.model.Perfil;

public abstract class CadastroContatoForm extends Form<Contato> {

	private static final long serialVersionUID = -6472581812156846712L;
	
	private FeedbackPanel feedback;
	private TextField<String> email;
	private TextField<String> telefone;
	private TextField<String> ddd;
	private Contato contato = new Contato();

	private TextField<String> cep;

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
		email.setModel(new PropertyModel<String>(contato, "email"));
		add(email);
		
		telefone = new TextField<String>("telefone");
		telefone.setModel(new PropertyModel<String>(contato, "telefone"));
		add(telefone);
		
		ddd = new TextField<String>("ddd");
		ddd.setModel(new PropertyModel<String>(contato, "ddd"));
		add(ddd);
		
		cep = new TextField<String>("cep");
		cep.setModel(new PropertyModel<String>(getEndereco(), "cep"));
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
				contato.setEndereco(getEndereco());
				cliente.setContato(contato);
				salvarCliente(cliente);
				success("Cadastrado com sucesso !");
				target.add(feedback);
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
	
}
