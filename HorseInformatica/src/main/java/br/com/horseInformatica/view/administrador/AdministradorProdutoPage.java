package br.com.horseInformatica.view.administrador;

import java.io.IOException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.model.Tipo;
import br.com.horseInformatica.service.ServiceProduto;
import br.com.horseInformatica.service.ServiceTipo;

public abstract class AdministradorProdutoPage extends AdministradorPage {

	private static final long serialVersionUID = -7797751310797545122L;
	
	@SpringBean
	private ServiceProduto serviceProduto;
	@SpringBean
	private ServiceTipo serviceTipo;
	private Produto produto;
	private TextField<String> nomeProduto;
	private TextField<Double> valorProduto;
	private FileUploadField imagemProduto;
	private FeedbackPanel feedback;
	private DropDownChoice<Tipo> tipo;
	

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
	
		tipo = new DropDownChoice<Tipo>("tipo", serviceTipo.findAll());
		tipo.setModel(new PropertyModel<Tipo>(getProduto(), "tipo"));
		tipo.setChoiceRenderer(new ChoiceRenderer<Tipo>("nome"));
		formulario.add(tipo);
		
		imagemProduto = new FileUploadField("imagem");
		//imagemProduto.setModel(new PropertyModel(getProduto(), "imagem"));
		formulario.add(imagemProduto);
		
		formulario.add(new AjaxButton("cadastrar"){

			private static final long serialVersionUID = -2741513535364626687L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String clientFileName = imagemProduto.getFileUpload().getClientFileName();
				getProduto().setCaminhoImagem("C://Users//yuri88//git//Repositorio-HorseInformatica//HorseInformatica//src//main//webapp//img//produtos//" + clientFileName);
				try {
					serviceProduto.gravarImagem(imagemProduto.getFileUpload().getInputStream(), getProduto().getCaminhoImagem());
				} catch (IOException e) {
					e.printStackTrace();
				}
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
