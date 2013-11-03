package br.com.horseInformatica.persistence.implementations;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;
import javax.persistence.Query;

import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.stereotype.Repository;

import br.com.horseInformatica.model.Produto;
import br.com.horseInformatica.persistence.generics.JpaGenericDao;
import br.com.horseInformatica.persistence.interfaces.IDaoProduto;

@Repository
public class DaoProdutoImpl extends JpaGenericDao<Produto> implements IDaoProduto {

	public DaoProdutoImpl() {
		super(Produto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarProdutosTipoBanco(Integer codigoTipo) {
		String jpql = "FROM Produto p where p.tipo.id = ?";
		
		Query query = super.createQuery(jpql,codigoTipo);

		return query.getResultList();
	}

	@Override
	public void gravarImagemDiretorio(InputStream imagemInputStream, String caminhoImagem) {
		File file2 = new File(caminhoImagem);
		try {
			FileInputStream fis = (FileInputStream) imagemInputStream;
			FileOutputStream fos = new FileOutputStream(file2);
			
			byte[] buf = new byte[1024];
			int len;
			
			while ((len = fis.read(buf)) > 0){
				fos.write(buf, 0, len);
			}
			
			fis.close();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] buscarImagem(String caminhoImagem) throws Exception {
			File file = new File(caminhoImagem);
		
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			byte[] buf = new byte[1024];
			
			while(fis.read(buf) != -1){
				out.write(buf);
			}
			return out.toByteArray();
		
	}
	
	
	
}
