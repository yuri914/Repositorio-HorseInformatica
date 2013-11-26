package br.com.horseInformatica.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import br.com.horseInformatica.util.enumerations.EnumSexo;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable
{

   private static final long serialVersionUID = 3867149330766509162L;

   @Id
   @GeneratedValue
   @Column(name = "id")
   private Integer id;

   @Column(name = "nome")
   private String nome;

   @Column(name = "cpf")
   private String cpf;

   @Column(name = "rg")
   private String rg;

   @Column(name = "dataNascimento")
   private Date dataNascimento;

   @ManyToOne
   @JoinColumn(name = "perfil", columnDefinition = "integer Default 1", insertable = false)
   private Perfil perfil;

   @Column(name = "login")
   private String login;

   @Column(name = "senha")
   private String senha;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "contato")
   private Contato contato;

   @Column(name = "dataCadastro")
   private Date dataCadastro;

   @Column(name = "sexo")
   @Enumerated(EnumType.STRING)
   private EnumSexo sexo;

   @Transient
   private Boolean checkSelecionada;

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }

   public String getRg()
   {
      return rg;
   }

   public void setRg(String rg)
   {
      this.rg = rg;
   }

   public Date getDataNascimento()
   {
      return dataNascimento;
   }

   public void setDataNascimento(Date dataNascimento)
   {
      this.dataNascimento = dataNascimento;
   }

   public Perfil getPerfil()
   {
      return perfil;
   }

   public void setPerfil(Perfil perfil)
   {
      this.perfil = perfil;
   }

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   public Contato getContato()
   {
      return contato;
   }

   public void setContato(Contato contato)
   {
      this.contato = contato;
   }

   public Date getDataCadastro()
   {
      return dataCadastro;
   }

   public void setDataCadastro(Date dataCadastro)
   {
      this.dataCadastro = dataCadastro;
   }

   public EnumSexo getSexo()
   {
      return sexo;
   }

   public void setSexo(EnumSexo sexo)
   {
      this.sexo = sexo;
   }

   public Integer getTelefoneCliente()
   {
      return getContato().getTelefone();
   }

   public Boolean getCheckSelecionada()
   {
      return checkSelecionada;
   }

   public void setCheckSelecionada(Boolean checkSelecionada)
   {
      this.checkSelecionada = checkSelecionada;
   }

   @Transient
   public Integer getIdade()
   {
      Date dataHoje = new Date();
      Calendar cal = Calendar.getInstance();

      cal.setTime(dataHoje);
      int day1 = cal.get(Calendar.DAY_OF_YEAR);
      int ano1 = cal.get(Calendar.YEAR);

      cal.setTime(getDataNascimento());
      int day2 = cal.get(Calendar.DAY_OF_YEAR);
      int ano2 = cal.get(Calendar.YEAR);

      int idade = ano1 - ano2;

      if (day1 < day2)
      {
         idade--;
      }

      return idade;
   }

   @Transient
   public String getTempoCliente()
   {
      Date dataHoje = new Date();
      Calendar cal = Calendar.getInstance();
      String tempoDeCadastro = null;

      cal.setTime(dataHoje);
      int day1 = cal.get(Calendar.DAY_OF_YEAR);
      int ano1 = cal.get(Calendar.YEAR);

      cal.setTime(getDataCadastro());
      int day2 = cal.get(Calendar.DAY_OF_YEAR);
      int ano2 = cal.get(Calendar.YEAR);

      int anosDeCadastro = ano1 - ano2;
      int diasCliente = day1 - day2;

      if (anosDeCadastro < 1)
      {

         if ((day1 - day2) > 30)
         {
            tempoDeCadastro = (diasCliente / 30) + " meses";
         }
         else
         {
            if (diasCliente != 0)
            {
               tempoDeCadastro = diasCliente + " dias";
            }
            else
            {
               tempoDeCadastro = "Cliente cadastrado hoje.";
            }
         }

      }
      else
      {
         tempoDeCadastro = anosDeCadastro + " anos";
      }

      return tempoDeCadastro;
   }
}
