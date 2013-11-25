package br.com.horseInformatica.view.administrador;

import java.util.List;
import org.apache.wicket.spring.injection.annot.SpringBean;
import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.service.ServiceCliente;

public class AdministradorClientePage extends AdministradorBasePage
{

   private static final long serialVersionUID = -5732317569309869221L;

   @SpringBean
   private ServiceCliente serviceCliente;

   public AdministradorClientePage(String mensagem)
   {
      add(new AdministradorClienteForm("formAdmCliente", mensagem)
      {

         private static final long serialVersionUID = -4530599058526072244L;

         @Override
         protected List<Cliente> buscarListaCliente()
         {
            return serviceCliente.findAll();
         }

         @Override
         protected List<Cliente> buscarClienteFiltro(String nomeClienteConsulta)
         {
            return serviceCliente.findClienteByName(nomeClienteConsulta);
         }

         @Override
         protected void excluirCliente(Cliente clienteAtual)
         {
            serviceCliente.delete(clienteAtual.getId());
         }

         @Override
         protected void atualizaCliente(Cliente clienteAtual)
         {
            serviceCliente.persist(clienteAtual);

         }

         @Override
         protected void excluirListaClientes(List<Cliente> listaItens)
         {
            serviceCliente.excluirListaClientesBanco(listaItens);
         }

      });
   }

}
