package br.com.horseInformatica.view.administrador;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.util.relatorios.RelatorioFactory;

public abstract class AdministradorClienteForm extends Form<Cliente>
{

   private static final long serialVersionUID = -5228865222034777465L;

   private final AdministradorClientePanel panelCliente;
   private final FeedbackPanel feedback;
   private final AjaxButton btConsultar;
   private final TextField<String> nomeCliente;
   private final Button btRelatorio;

   private final ModalWindow modalDetail;

   public AdministradorClienteForm(String id, String mensagem)
   {
      super(id);

      feedback = new FeedbackPanel("mensages");
      feedback.setOutputMarkupId(true);
      add(feedback);
      if (mensagem != null)
      {
         success(mensagem);
      }

      nomeCliente = new TextField<String>("nomeCliente");
      nomeCliente.setModel(new Model<String>());
      add(nomeCliente);

      panelCliente = new AdministradorClientePanel("panelClientes")
      {

         private static final long serialVersionUID = 315182770665539622L;

         @Override
         protected void excluirCliente(Cliente clienteAtual, AjaxRequestTarget target)
         {
            AdministradorClienteForm.this.excluirCliente(clienteAtual);
            panelCliente.setGridCliente(buscarListaCliente());
            target.add(panelCliente);
         }

         @Override
         protected void exibirDetalhesCliente(Cliente clienteAtual, AjaxRequestTarget target)
         {
            modalDetail.setContent(new ModalDetailPanel(modalDetail.getContentId(), clienteAtual));
            modalDetail.show(target);
         }

         @Override
         protected void atualizarCliente(Cliente clienteAtual, AjaxRequestTarget target)
         {
            setResponsePage(new AdministradorAtualizaClientePage(clienteAtual));
         }
      };
      panelCliente.setGridCliente(buscarListaCliente());
      panelCliente.setOutputMarkupId(true);
      add(panelCliente);

      modalDetail = new ModalWindow("modalDetalhes");
      modalDetail.setInitialWidth(400);
      modalDetail.setInitialHeight(400);
      add(modalDetail);

      btConsultar = new AjaxButton("consultar")
      {

         private static final long serialVersionUID = -6834425602136707880L;

         @Override
         protected void onSubmit(AjaxRequestTarget target, Form<?> form)
         {
            List<Cliente> listaClienteFiltrado = buscarClienteFiltro(nomeCliente.getModelObject());
            if (listaClienteFiltrado.size() != 0)
            {
               panelCliente.setGridCliente(listaClienteFiltrado);
            }
            else
            {
               error("Nenhum cliente encontrado com o nome informado.");
            }
            target.add(panelCliente, feedback);
         }
      };
      add(btConsultar);

      btRelatorio = new Button("btRelatorio")
      {

         private static final long serialVersionUID = 982871553366907236L;

         @Override
         public void onSubmit()
         {
            String relatorioURL =
               "C:\\Users\\yuri88\\git\\Repositorio-HorseInformatica\\HorseInformatica\\src\\main\\java\\br\\com\\horseInformatica\\util\\relatorios\\RelatorioClienteHorseInfo.jasper";
            HttpServletRequest req = (HttpServletRequest) getRequest().getContainerRequest();
            req.getServletPath();
            String pdfFileName = "Relatorio";
            RelatorioFactory.gerarRelatorio((HttpServletResponse) getResponse()
               .getContainerResponse(), buscarListaCliente(), relatorioURL, null, pdfFileName);
         }
      };
      add(btRelatorio);
   }

   protected abstract void atualizaCliente(Cliente clienteAtual);

   protected abstract List<Cliente> buscarClienteFiltro(String nomeClienteConsulta);

   protected abstract void excluirCliente(Cliente clienteAtual);

   protected abstract List<Cliente> buscarListaCliente();

}
