package br.com.horseInformatica.view.produtos;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.ResourceReference;

import br.com.horseInformatica.model.Produto;

public abstract class ProdutoPanel extends Panel {

	private static final long serialVersionUID = 7889031519398250926L;

	public ProdutoPanel(String id) {
		super(id);
		
	}
	
	public void getPanelProduto(List<Produto> listaProdutos){
		DataView<Produto> repetidor = new DataView<Produto>("produtos", new ListDataProvider<Produto>(listaProdutos), 10){

			private static final long serialVersionUID = 6244997058831115970L;

			@Override
			protected void populateItem(Item<Produto> item) {
				Produto produto = item.getModelObject();
				ByteArrayResource resource = new ByteArrayResource("image/jpeg", buscarImagem(produto.getCaminhoImagem()));
				item.add(new Image("imagem", resource));
				item.add(new Label("nome", produto.getNome()));
			}
		};
		add(new PagingNavigator("paginacao", repetidor));
		add(repetidor);
	}

	protected abstract byte[] buscarImagem(String caminhoImagem);
}
