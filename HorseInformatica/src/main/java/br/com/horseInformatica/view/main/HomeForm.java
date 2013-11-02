package br.com.horseInformatica.view.main;

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
import br.com.horseInformatica.view.administrador.AdministradorPage;
import br.com.horseInformatica.view.base.BasePage;
import br.com.horseInformatica.view.contato.CadastroContatoPage;

public abstract class HomeForm extends Form<Cliente> {

	private static final long serialVersionUID = 1434251727526599430L;

	private FeedbackPanel feedback;
	private TextField<String> loginTxtField;
	private PasswordTextField senhaTxtField;
	private AjaxButton btEntrar;
	private TextField<String> nome;
	private RadioChoice<EnumSexo> genero;
	private DateTextField nascimento;
	private TextField<String> cpf;
	private TextField<String> rg;
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
		loginTxtField.setModel(new PropertyModel<String>(getClienteLogin(), "login"));
		add(loginTxtField);

		senhaTxtField = new PasswordTextField("senha");
		senhaTxtField.setModel(new PropertyModel<String>(getClienteLogin(), "senha"));
		senhaTxtField.setRequired(false);
		add(senhaTxtField);

		nome = new TextField<String>("nome");
		nome.setModel(new PropertyModel<String>(getClienteCadastro(), "nome"));
		add(nome);

		cpf = new TextField<String>("cpf");
		cpf.setModel(new PropertyModel<String>(getClienteCadastro(), "cpf"));
		add(cpf);

		rg = new TextField<String>("rg");
		rg.setModel(new PropertyModel<String>(getClienteCadastro(), "rg"));
		add(rg);

		nascimento = new DateTextField("dataNascimento", new PropertyModel<Date>(getClienteCadastro(), "dataNascimento"), new StyleDateConverter("S-", true));

		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		nascimento.add(datePicker);
		add(nascimento);

		genero = new RadioChoice<EnumSexo>("genero");
		genero.setChoices(Arrays.asList(EnumSexo.values()));
		genero.setChoiceRenderer(new ChoiceRenderer<EnumSexo>("descricao"));
		genero.setModel(new PropertyModel<EnumSexo>(getClienteCadastro(), "sexo"));
		genero.setSuffix("");
		add(genero);

		btEntrar = new AjaxButton("entrar") {

			private static final long serialVersionUID = -7629953510484947562L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Cliente clienteEncontrado = autenticarCliente(getClienteLogin());
				if (verificarLoginSenha()){
					if (clienteEncontrado != null) {
						getSession().setAttribute("clienteSessao", clienteEncontrado);
						if(clienteEncontrado.getLogin().equals("admin")){
							setResponsePage(AdministradorPage.class);
						}else {
							setResponsePage(BasePage.class);
						}
					} else {
						error("Usuario ou senha inválido.");
					}
				} else {
					error("Informe login e senha.");
				}
				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		};
		add(btEntrar);

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
				getSession().setAttribute("usuarioSessao", getClienteCadastro());
				setResponsePage(new CadastroContatoPage(getClienteCadastro()));
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

	private boolean verificarLoginSenha() {
		boolean verificou = true;
		if (loginTxtField.getModelObject() == null && senhaTxtField.getModelObject() == null) {
			verificou = false;
		}

		else if (loginTxtField.getModelObject() == null || senhaTxtField.getModelObject() == null) {
			verificou = false;
		}
		return verificou;
	}

}
