/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    //método que soluciona el problema con los acentos y la ñ en las vistas 
    /**
     *
     * @param mapping
     * @param request
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Root.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     /* método para eliminar los atributos con session excepto el pasado como parametro,
     * user y formUsuario*/
    public static void deleteSessions(HttpServletRequest _request, String atrib) {
        String atributo;
        HttpSession session = _request.getSession();
        Enumeration e = session.getAttributeNames();

        while (e.hasMoreElements()) {

            atributo = (String) e.nextElement();

            if (!atributo.equals("usuarioForm") && !atributo.equals("user")
                    && !atributo.equals(atrib)) {
                System.out.println("Eliminado el atributo con scope session " + atributo);
                session.removeAttribute(atributo);
            }
        }
    }
    
    /* método para eliminar los atributos con session excepto los pasados como parametro,
     * user y formUsuario*/
    public static void deleteSessions(HttpServletRequest _request, String atrib1, String atrib2) {
        String atributo;
        HttpSession session = _request.getSession();
        Enumeration e = session.getAttributeNames();

        while (e.hasMoreElements()) {

            atributo = (String) e.nextElement();

            if (!atributo.equals("usuarioForm") && !atributo.equals("user")
                    && !atributo.equals(atrib1) && !atributo.equals(atrib2)) {
                System.out.println("Eliminado el atributo con scope session " + atributo);
                session.removeAttribute(atributo);
            }
        }
    }
}
