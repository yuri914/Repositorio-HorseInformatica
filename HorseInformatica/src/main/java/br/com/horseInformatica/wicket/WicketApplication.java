package br.com.horseInformatica.wicket;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import br.com.horseInformatica.view.main.HomePage;


/**
 * ApplicatioN object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see br.com.horseInformatica.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
}
