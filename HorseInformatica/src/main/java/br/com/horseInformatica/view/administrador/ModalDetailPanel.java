package br.com.horseInformatica.view.administrador;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import br.com.horseInformatica.model.Cliente;

public class ModalDetailPanel extends Panel
{

   private static final long serialVersionUID = 580237492352146232L;
   private final Label lblNome;
   private final Label lblCpf;
   private final Label lblRg;
   private final Label lblSexo;
   private final Label lblIdade;
   private final Label lblPerfil;
   private final Label lblTempoCliente;
   private final Label lblLogradouro;
   private final Label lblBairro;
   private final Label lblTelefone;
   private final Label lblEmail;

   public ModalDetailPanel(String id, Cliente cliente)
   {
      super(id);

      lblNome = new Label("nome");
      lblNome.setDefaultModel(new Model<String>(cliente.getNome()));
      add(lblNome);

      lblSexo = new Label("sexo");
      lblSexo.setDefaultModel(new Model<String>(cliente.getSexo().getDescricao()));
      add(lblSexo);

      lblCpf = new Label("cpf");
      lblCpf.setDefaultModel(new Model<String>(cliente.getCpf()));
      add(lblCpf);

      lblRg = new Label("rg");
      lblRg.setDefaultModel(new Model<String>(cliente.getRg()));
      add(lblRg);

      lblIdade = new Label("idade");
      lblIdade.setDefaultModel(new Model<Integer>(cliente.getIdade()));
      add(lblIdade);

      lblPerfil = new Label("perfil");
      lblPerfil.setDefaultModel(new Model<String>(cliente.getPerfil().getNome()));
      add(lblPerfil);

      lblTempoCliente = new Label("tempoCliente");
      lblTempoCliente.setDefaultModel(new Model<String>(cliente.getTempoCliente()));
      add(lblTempoCliente);

      lblLogradouro = new Label("logradouro");
      lblLogradouro.setDefaultModel(new Model<String>(cliente.getContato().getEndereco()
         .getLogradouro()));
      add(lblLogradouro);

      lblBairro = new Label("bairro");
      lblBairro.setDefaultModel(new Model<String>(cliente.getContato().getEndereco().getBairro()));
      add(lblBairro);

      lblTelefone = new Label("telefone");
      lblTelefone.setDefaultModel(new Model<Integer>(cliente.getContato().getTelefone()));
      add(lblTelefone);

      lblEmail = new Label("email");
      lblEmail.setDefaultModel(new Model<String>(cliente.getContato().getEmail()));
      add(lblEmail);

   }

}
