package br.com.horseInformatica.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoProduto;

@Service("serviceProduto")
public class ServiceProduto extends GenericoService<Produto> implements Serializable
{

   private static final long serialVersionUID = 4265708673164806107L;

   @Autowired
   private IDaoProduto daoProduto;

   @Override
   protected GenericDao<Produto> getDao()
   {
      return daoProduto;
   }

   public List<Produto> listarProdutos(Integer codigoTipo)
   {
      return ((IDaoProduto) getDao()).listarProdutosTipoBanco(codigoTipo);
   }
}
