package br.com.horseInformatica.view.administrador;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

import br.com.horseInformatica.view.main.HomePage;

public class AdministradorPage extends WebPage {

	private static final long serialVersionUID = -3196522595163082628L;

	public AdministradorPage(){
		
		add(new AjaxLink<Void>("produtos"){

			private static final long serialVersionUID = -4616557070666546296L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new AdministradorProdutoPage() {
					private static final long serialVersionUID = 8361089168930925729L;
				});
			}
		});
		
		add(new AjaxLink<Void>("clientes"){

			private static final long serialVersionUID = -4616557070666546296L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(AdministradorClientePage.class);
			}
		});
		
		add(new AjaxLink<Void>("sair"){

			private static final long serialVersionUID = -4616557070666546296L;

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
