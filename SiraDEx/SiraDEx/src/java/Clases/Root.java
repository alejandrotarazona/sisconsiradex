/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author SisCon
 */
public class Root extends ActionForm {
    String mensaje;

    public String getMensaje() {
        return mensaje;
    }
  
  public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
  }
      //este método es para solucionar el problema con los acentos y la ñ en las vistas 
      public void reset(ActionMapping mapping, HttpServletRequest request) {
        System.out.println("---- Entrada en reset(...) ----");

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
    }
  
}
