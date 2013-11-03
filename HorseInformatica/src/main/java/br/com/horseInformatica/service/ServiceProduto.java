package br.com.horseInformatica.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.GenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoProduto;

@Service("serviceProduto")
public class ServiceProduto extends GenericoService<Produto> implements Serializable{

	private static final long serialVersionUID = 4265708673164806107L;

	@Autowired
	private IDaoProduto daoProduto;
	@Override
	protected GenericDao<Produto> getDao() {
		return daoProduto;
	}

	public List<Produto> listarProdutos(Integer codigoTipo) {
		
		return ((IDaoProduto) getDao()).listarProdutosTipoBanco(codigoTipo);
	}

	public void gravarImagem(InputStream imagemInputStream, String caminhoImagem) {
		((IDaoProduto) getDao()).gravarImagemDiretorio(imagemInputStream, caminhoImagem);
	}

	public byte[] buscarImagem(String caminhoImagem) {
		byte[] byteImagem = null;
		try {
			byteImagem = ((IDaoProduto) getDao()).buscarImagem(caminhoImagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteImagem;
	}

}
