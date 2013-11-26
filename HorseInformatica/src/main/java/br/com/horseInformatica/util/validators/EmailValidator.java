package br.com.horseInformatica.util.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;

public class EmailValidator implements IValidator<String>
{

   private static final long serialVersionUID = -3903631018706027480L;

   private final String email;
   
   public EmailValidator(String email)
   {
      this.email = email;
   }

   @Override
   public void validate(IValidatable<String> validatable){}

   public Boolean validar(){
      
      Boolean isValido = false;
      
      Pattern p = Pattern.compile(".+@.+\\.[a-z]+");  
      
      Matcher m = p.matcher(email);  
  
      boolean matchFound = m.matches();  
  
      if (matchFound)
      {
         isValido = true;
      }
      
      return isValido;
   }  
}
