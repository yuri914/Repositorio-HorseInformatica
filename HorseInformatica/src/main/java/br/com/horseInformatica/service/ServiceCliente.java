package br.com.horseInformatica.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoCliente;

@Service("serviceCliente")
public class ServiceCliente extends GenericoService<Cliente> implements Serializable
{

   private static final long serialVersionUID = 285941439575888475L;

   @Autowired
   private IDaoCliente daoCliente;

   @Override
   protected GenericDao<Cliente> getDao()
   {
      return daoCliente;
   }

   public Cliente autenticarClienteBanco(Cliente clienteLogin)
   {
      Cliente clienteEncontrado = null;
      List<Cliente> listaCliente = daoCliente.autenticarCliente(clienteLogin);
      if (listaCliente.size() != 0)
      {
         clienteEncontrado = listaCliente.get(0);
      }
      return clienteEncontrado;
   }

   public List<Cliente> findClienteByName(String nomeClienteConsulta)
   {

      return ((IDaoCliente) getDao()).findClienteByNameDatabase(nomeClienteConsulta);
   }

   public void atualizar(Cliente cliente)
   {

      getDao().update(cliente);

   }

   public void excluirListaClientesBanco(List<Cliente> listaItens)
   {
      for (Cliente cliente : listaItens)
      {
         getDao().delete(cliente.getId());
      }
   }

   public void atualizarListaClientesBanco(List<Cliente> listaItens)
   {
      for (Cliente cliente : listaItens)
      {
         getDao().update(cliente);
      }
   }

}
