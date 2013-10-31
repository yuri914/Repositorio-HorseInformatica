package br.com.horseInformatica.view;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.util.enumerations.EnumSexo;

public abstract class HomeForm extends Form<Cliente> {

	private static final long serialVersionUID = 1434251727526599430L;

	private FeedbackPanel feedback;
	private TextField<String> loginTxtField;
	private PasswordTextField senhaTxtField;
	private AjaxButton btEntrar;
	private TextField<String> nome;
	private RadioChoice<EnumSexo> genero;
	private DateTextField nascimento;
	private TextField<Long> cpf;
	private TextField<String> loginCadastro;
	private TextField<String> senhaCadastro;
	private AjaxButton btCadastrar;
	Cliente clienteLogin;
	Cliente clienteCadastro;

	public HomeForm(String id) {
		super(id);

		feedback = new FeedbackPanel("mensagem");
		feedback.setOutputMarkupId(true);
		add(feedback);

		loginTxtField = new TextField<String>("login");
		add(loginTxtField);

		senhaTxtField = new PasswordTextField("senha");
		senhaTxtField.setModel(new PropertyModel<String>(getClienteLogin(), "senha"));
		senhaTxtField.setRequired(false);
		add(senhaTxtField);

		btEntrar = new AjaxButton("entrar") {

			private static final long serialVersionUID = -7629953510484947562L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Cliente usuarioEncontrado = autenticarCliente(getClienteLogin());
				if (usuarioEncontrado != null) {
					getSession().setAttribute("usuarioSessao", usuarioEncontrado);

				} else {
					error("Usuario ou senha inválidos!");
					target.add(feedback);
				}
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		};
		add(btEntrar);

		nome = new TextField<String>("nome");
		nome.setModel(new PropertyModel<String>(getClienteCadastro(), "nome"));
		add(nome);

		genero = new RadioChoice<EnumSexo>("genero");
		genero.setChoices(Arrays.asList(EnumSexo.values()));
		genero.setChoiceRenderer(new ChoiceRenderer<EnumSexo>("descricao"));
		genero.setModel(new PropertyModel<EnumSexo>(getClienteCadastro(), "genero"));
		genero.setSuffix("");
		add(genero);

		nascimento = new DateTextField("dataNascimento",
		new PropertyModel<Date>(getClienteCadastro(), "dataNascimento"), new StyleDateConverter("S-", true));
		
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		nascimento.add(datePicker);
		add(nascimento);

		cpf = new TextField<Long>("cpf");
		cpf.setModel(new PropertyModel<Long>(getClienteCadastro(), "cpf"));
		add(cpf);

		loginCadastro = new TextField<String>("loginCadastro");
		loginCadastro.setModel(new PropertyModel<String>(getClienteCadastro(), "login"));
		add(loginCadastro);

		senhaCadastro = new TextField<String>("senhaCadastro");
		senhaCadastro.setModel(new PropertyModel<String>(getClienteCadastro(), "senha"));
		add(senhaCadastro);

		btCadastrar = new AjaxButton("cadastrar") {

			private static final long serialVersionUID = -4309959029136917439L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				salvarCliente(getClienteCadastro());
				getSession().setAttribute("usuarioSessao", getClienteCadastro());
				// setResponsePage(ContatoCadastroPage.class);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}

		};
		add(btCadastrar);
	}

	protected abstract Cliente autenticarCliente(Cliente clienteLogin);

	protected abstract void salvarCliente(Cliente cliente);

	public Cliente getClienteLogin() {
		if (clienteLogin == null) {
			clienteLogin = new Cliente();
		}
		return clienteLogin;
	}

	public Cliente getClienteCadastro() {
		if (clienteCadastro == null) {
			clienteCadastro = new Cliente();
		}
		return clienteCadastro;
	}

}
