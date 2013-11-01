package br.com.horseInformatica.view.index;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 6718941760776214183L;

	public BasePage(){
		
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
	
}
