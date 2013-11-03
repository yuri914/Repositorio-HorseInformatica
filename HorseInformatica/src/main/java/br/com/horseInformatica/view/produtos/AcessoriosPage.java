package br.com.horseInformatica.view.produtos;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.service.ServiceProduto;
import br.com.horseInformatica.view.base.BasePage;

public class AcessoriosPage extends BasePage {

	private static final long serialVersionUID = -5807678391882479331L;
	
	private ProdutoPanel produtosPanel;

	@SpringBean
	private ServiceProduto serviceProduto;
	public AcessoriosPage(){
		
		produtosPanel = new ProdutoPanel("produtosPanel"){

			private static final long serialVersionUID = -2010160888899632424L;

			@Override
			protected byte[] buscarImagem(String caminhoImagem) {
				return buscarImagemDiretorio(caminhoImagem);
			}
			
		};
		produtosPanel.getPanelProduto(listarProdutosTipo(3));
		add(produtosPanel);
	}
	
	protected byte[] buscarImagemDiretorio(String caminhoImagem) {
		return serviceProduto.buscarImagem(caminhoImagem);
	}

	private List<Produto> listarProdutosTipo(Integer codigoTipo) {
		return serviceProduto.listarProdutos(codigoTipo);
	}
}
