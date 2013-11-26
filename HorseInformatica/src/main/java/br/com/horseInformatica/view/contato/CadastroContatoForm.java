package br.com.horseInformatica.view.contato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
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
import br.com.horseInformatica.util.validators.EmailValidator;
import br.com.horseInformatica.view.index.IndexPage;

public abstract class CadastroContatoForm extends Form<Contato>
{

   private static final long serialVersionUID = -6472581812156846712L;

   private Contato contato;
   private final FeedbackPanel feedback;
   private final TextField<String> email;
   private final TextField<String> telefone;
   private final DropDownChoice<Integer> ddd;
   private final TextField<Integer> cep;
   private final TextField<String> logradouro;
   private final TextField<String> bairro;
   private final TextField<String> cidade;
   private final TextField<String> estado;
   private final AjaxButton btConfirmar;

   public CadastroContatoForm(String id, final Cliente cliente)
   {
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

      ddd = new DropDownChoice<Integer>("ddd");
      List<Integer> listaDdd = new ArrayList<Integer>(Arrays.asList(21, 11, 71, 85, 51, 31));
      ddd.setChoices(listaDdd);
      ddd.setModel(new PropertyModel<Integer>(getContato(), "ddd"));
      add(ddd);

      cep = new TextField<Integer>("cep");
      cep.setModel(new PropertyModel<Integer>(getEndereco(), "cep"));
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

      btConfirmar = new AjaxButton("confirmar")
      {

         private static final long serialVersionUID = -4309959029136917439L;

         @Override
         protected void onSubmit(AjaxRequestTarget target, Form<?> form)
         {
            if (validarCampos())
            {
               if (new EmailValidator(email.getModelObject()).validar())
               {
                  getEndereco().setLogradouro(logradouro.getModelObject());
                  getEndereco().setBairro(bairro.getModelObject());
                  getEndereco().setCidade(cidade.getModelObject());
                  getEndereco().setEstado(estado.getModelObject());
                  getContato().setEndereco(getEndereco());
                  cliente.setContato(getContato());
                  salvarCliente(cliente);
                  getSession().setAttribute("clienteSessao", cliente);
                  setResponsePage(IndexPage.class);
               }
               else
               {
                  error("Email inválido.");
                  target.add(feedback);
               }
            }
            else
            {
               error("Preencha todos os campos.");
               target.add(feedback);
            }
         }

         @Override
         protected void onError(AjaxRequestTarget target, Form<?> form)
         {
            System.out.println("Erro");
            target.add(feedback);
         }

      };
      add(btConfirmar);
   }

   protected boolean validarCampos()
   {
      boolean isValido = true;
      isValido &= String.valueOf(cep.getValue()).trim().length() > 0;
      isValido &= logradouro.getValue().trim().length() > 0;
      isValido &= bairro.getValue().trim().length() > 0;
      isValido &= email.getValue().trim().length() > 0;
      isValido &= telefone.getValue().trim().length() > 0;
      isValido &= String.valueOf(ddd.getValue()).trim().length() > 0;
      isValido &= estado.getValue().trim().length() > 0;
      isValido &= cidade.getValue().trim().length() > 0;
      return isValido;
   }

   protected abstract void salvarContato(Contato contato);

   protected abstract void salvarEndereco(Endereco endereco);

   protected abstract void salvarPerfil(Perfil perfil);

   protected abstract void salvarCliente(Cliente cliente);

   Endereco endereco;

   public Endereco getEndereco()
   {
      if (endereco == null)
      {
         endereco = new Endereco();
      }
      return endereco;
   }

   public Contato getContato()
   {
      if (contato == null)
      {
         contato = new Contato();
      }
      return contato;
   }

}
