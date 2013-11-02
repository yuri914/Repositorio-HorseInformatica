package br.com.horseInformatica.view.administrador;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.service.ServiceProduto;

public abstract class AdministradorProdutoPage extends AdministradorPage {

	private static final long serialVersionUID = -7797751310797545122L;
	
	@SpringBean
	private ServiceProduto serviceProduto;
	private Produto produto;
	private TextField<String> nomeProduto;
	private TextField<Double> valorProduto;
	private FileUploadField imagemProduto;
	private FeedbackPanel feedback;
	

	public AdministradorProdutoPage(){
		
		Form<Produto> formulario = new Form<Produto>("formAdmProduto");
		add(formulario);
		
		feedback = new FeedbackPanel("mensagens");
		feedback.setOutputMarkupId(true);
		formulario.add(feedback);
		
		nomeProduto = new TextField<String>("nome");
		nomeProduto.setModel(new PropertyModel<String>(getProduto(), "nome"));
		formulario.add(nomeProduto);
		
		valorProduto = new TextField<Double>("valor");
		valorProduto.setModel(new PropertyModel<Double>(getProduto(), "valor"));
		formulario.add(valorProduto);
		
		imagemProduto = new FileUploadField("imagem");
		//imagemProduto.setModel(new PropertyModel(getProduto(), "imagem"));
		formulario.add(imagemProduto);
		
		formulario.add(new AjaxButton("cadastrar"){

			private static final long serialVersionUID = -2741513535364626687L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				getProduto().setImagem(imagemProduto.getFileUpload().getBytes());
				serviceProduto.persist(getProduto());
				success("Produto cadastrado com sucesso");
				target.add(feedback);
			}
		});
	}

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}
	
}
