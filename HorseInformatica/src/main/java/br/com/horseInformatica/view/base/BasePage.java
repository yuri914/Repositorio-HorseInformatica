package br.com.horseInformatica.view.base;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Cliente;
import br.com.horseInformatica.service.ServiceProduto;
import br.com.horseInformatica.view.main.HomePage;
import br.com.horseInformatica.view.produtos.AcessoriosPage;
import br.com.horseInformatica.view.produtos.ComputadoresPage;
import br.com.horseInformatica.view.produtos.SmartphonesPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 6718941760776214183L;
	
	@SpringBean
	private ServiceProduto serviceProduto;
	private Label saudacao;

	public BasePage(){
		
		Cliente clienteSessao = (Cliente) getSession().getAttribute("clienteSessao");
		
		saudacao = new Label("saudacaoCliente");
		saudacao.setDefaultModel(Model.of("Bem vindo, " + clienteSessao.getNome()));
		add(saudacao);
		
		add(new AjaxLink<Void>("smartphones"){

			private static final long serialVersionUID = 4135895316768093975L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(SmartphonesPage.class);
			}
			
		});
		
		add(new AjaxLink<Void>("computadores"){

			private static final long serialVersionUID = 1803183884188641533L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ComputadoresPage.class);
			}
			
		});
		
		add(new AjaxLink<Void>("acessorios"){

			private static final long serialVersionUID = 265302040222864545L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(AcessoriosPage.class);
			}
			
		});
		
		add(new AjaxLink<Void>("sair"){

			private static final long serialVersionUID = 265302040222864545L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				getSession().invalidate();
				setResponsePage(HomePage.class);
			}
		});
		
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
	
}
