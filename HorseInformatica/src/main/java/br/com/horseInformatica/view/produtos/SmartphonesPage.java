package br.com.horseInformatica.view.produtos;

import java.util.List;

import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.service.ServiceProduto;
import br.com.horseInformatica.view.base.BasePage;

public class SmartphonesPage extends BasePage {

	private static final long serialVersionUID = -46709913024963419L;
	@SpringBean
	private ServiceProduto serviceProduto;
	private ProdutoPanel produtosPanel;

	public SmartphonesPage(){
		produtosPanel = new ProdutoPanel("produtosPanel"){

			private static final long serialVersionUID = -2446906638226029677L;

			@Override
			protected byte[] buscarImagem(String caminhoImagem) {
				return buscarImagemDiretorio(caminhoImagem);
			}
		};
		produtosPanel.getPanelProduto(listarProdutosTipo(1));
		add(produtosPanel);
	}

	private List<Produto> listarProdutosTipo(Integer codigoTipo) {
		return serviceProduto.listarProdutos(codigoTipo);
	}
	
}
