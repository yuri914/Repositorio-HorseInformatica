package br.com.horseInformatica.view.produtos;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.service.ServiceProduto;
import br.com.horseInformatica.view.base.BasePage;

public class ComputadoresPage extends BasePage {

	private static final long serialVersionUID = -4380869311758199337L;

	@SpringBean
	private ServiceProduto serviceProduto;

	private ProdutoPanel produtosPanel;
	
	public ComputadoresPage(){
		
		produtosPanel = new ProdutoPanel("produtosPanel");
		produtosPanel.getPanelProduto(listarProdutosTipo(2));
		add(produtosPanel);
	}

	private List<Produto> listarProdutosTipo(Integer codigoTipo) {
		return serviceProduto.listarProdutos(codigoTipo);
	}
}
