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
import br.com.horseInformatica.view.base.BasePage;

public abstract class AdmistradorAtualizaClienteForm extends Form<Cliente>
{

   private static final long serialVersionUID = 2535896341184251584L;

   private final FeedbackPanel feedback;

   // Dados Pessoais
   private final TextField<String> nome;
   private final RadioChoice<EnumSexo> genero;
   private final DateTextField nascimento;
   private final TextField<String> cpf;
   private final TextField<String> rg;

   // Contato
   private final TextField<String> email;
   private final TextField<String> telefone;
   private final TextField<Integer> ddd;
   private final TextField<Integer> cep;
   private final TextField<String> logradouro;
   private final TextField<String> bairro;
   private final TextField<String> cidade;
   private final TextField<String> estado;
   private final TextField<String> login;
   private final TextField<String> senhaAtual;
   private final TextField<String> senha;
   private final AjaxButton btConfirmar;
   private Cliente clienteAtualizar;

   public AdmistradorAtualizaClienteForm(String id, final Cliente cliente)
   {
      super(id);

      setClienteAtualizar(cliente);
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

      nascimento =
         new DateTextField("dataNascimento", new PropertyModel<Date>(cliente, "dataNascimento"),
            new StyleDateConverter("S-", true));

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

      login = new TextField<String>("login");
      login.setModel(new PropertyModel<String>(cliente, "login"));
      add(login);

      senhaAtual = new TextField<String>("senhaAtual");
      senhaAtual.setModel(new Model(""));
      add(senhaAtual);

      senha = new TextField<String>("senha");
      senha.setModel(new Model(""));
      add(senha);

      email = new TextField<String>("email");
      email.setModel(new PropertyModel<String>(cliente.getContato(), "email"));
      add(email);

      telefone = new TextField<String>("telefone");
      telefone.setModel(new PropertyModel<String>(cliente.getContato(), "telefone"));
      add(telefone);

      ddd = new TextField<Integer>("ddd");
      ddd.setModel(new PropertyModel<Integer>(cliente.getContato(), "ddd"));
      add(ddd);

      cep = new TextField<Integer>("cep");
      cep.setModel(new PropertyModel<Integer>(cliente.getContato().getEndereco(), "cep"));
      cep.add(new AjaxFormComponentUpdatingBehavior("onblur")
      {

         private static final long serialVersionUID = -1358699233483750529L;

         @Override
         protected void onUpdate(AjaxRequestTarget target)
         {
            EnderecoTO enderecoCep = AuxiliarService.getEnderecoWebService(cep.getModelObject());
            if (enderecoCep != null)
            {
               logradouro.setModel(Model.of(enderecoCep.getLogradouro()));
               bairro.setModel(Model.of(enderecoCep.getBairro()));
               estado.setModel(Model.of(enderecoCep.getEstado()));
               cidade.setModel(Model.of(enderecoCep.getCidade()));
            }
            else
            {
               error("Cep não encontrado.");
            }
            target.add(logradouro, bairro, estado, cidade, feedback);
         }
      });
      add(cep);

      logradouro = new TextField<String>("logradouro");
      logradouro.setModel(new PropertyModel<String>(cliente.getContato().getEndereco(),
         "logradouro"));
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

      btConfirmar = new AjaxButton("confirmar")
      {

         private static final long serialVersionUID = 1L;

         @Override
         protected void onSubmit(AjaxRequestTarget target, Form<?> form)
         {
            if (senhaAtual.getModelObject().equals(getClienteAtualizar().getSenha()))
            {
               cliente.setSenha(senha.getModelObject());
               atualizarCliente(cliente);
               target.appendJavaScript("alert('Cliente atualizado com sucesso!')");

               setResponsePage(new BasePage());
            }
            else
            {
               target.appendJavaScript("alert('Senha atual informada inválida!')");
            }
            target.add(feedback);
         }

      };
      add(btConfirmar);

   }

   public Cliente getClienteAtualizar()
   {
      if (clienteAtualizar == null)
      {
         clienteAtualizar = new Cliente();

      }

      return clienteAtualizar;
   }

   public void setClienteAtualizar(Cliente clienteAtualizar)
   {
      this.clienteAtualizar = clienteAtualizar;
   }

   protected abstract void atualizarCliente(Cliente cliente);
}
