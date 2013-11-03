package br.com.horseInformatica.persistence.interfaces;

import java.io.InputStream;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.request.resource.ResourceReference;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.GenericDao;

public interface IDaoProduto extends GenericDao<Produto>{

	List<Produto> listarProdutosTipoBanco(Integer codigoTipo);
	void gravarImagemDiretorio(InputStream imagemInputStream, String caminhoImagem);
	byte[] buscarImagem(String caminhoImagem) throws Exception;
}
