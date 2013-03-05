/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
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

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
    }
    /* método para eliminar los forms con session excepto el formUsuario

    public void deleteSessions(HttpServletRequest _request) {
        _request.getSession().removeAttribute("actividadForm");
        _request.getSession().removeAttribute("tipoActividadForm");
        _request.getSession().removeAttribute("catalogoForm");
        _request.getSession().removeAttribute("elementoCatalogoForm");
    }*/

    /* método para eliminar los forms con session excepto el formUsuario y user*/
    
    public void deleteSessions(HttpServletRequest _request) {
        String atributo;
        HttpSession session = _request.getSession();
        Enumeration e = session.getAttributeNames();

        while (e.hasMoreElements()) {

            atributo = (String) e.nextElement();

            if (!atributo.equals("usuarioForm") && !atributo.equals("user")) {
                System.out.println("Eliminado el atributo con scope session "+atributo);
                session.removeAttribute(atributo);
            }
        }
    }
}
