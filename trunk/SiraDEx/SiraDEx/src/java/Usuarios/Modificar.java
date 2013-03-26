/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Clases.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author SisCon
 */
public class Modificar extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String PAGE = "page";

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=page, where "method" is the value specified
     * in <action> element : ( <action parameter="method" .../> )
     */
    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsuario();
        System.out.println("El usuario elegido es: " + u.toString());
        request.setAttribute("usuarioM", u);
        request.getSession().setAttribute("viejoUsuario", u);
        return mapping.findForward(PAGE);
    }

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=modificar, where "method" is the value
     * specified in <action> element : ( <action parameter="method" .../> )
     */
    public ActionForward modificar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario aux1 = (Usuario) form;
        System.out.println("El nuevo usuario es: "+aux1.toString());
        Usuario viejoUsuario = (Usuario) request.getSession().getAttribute("viejoUsuario");
        System.out.println("El viejo usuario es: "+viejoUsuario.toString());
        Usuario nuevoUsuario = viejoUsuario.clone();
        nuevoUsuario.setRol(aux1.getRol());
        
        if(nuevoUsuario.getRol().equalsIgnoreCase("DEx")){
            nuevoUsuario.setUsername(aux1.getUsername());
        }
        boolean b;

        try {
            b = nuevoUsuario.modificar(viejoUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }

        ArrayList<Usuario> usrs = Clases.Usuario.listarUsuario();
        request.setAttribute("usuarios", usrs);

        if (b) {
            nuevoUsuario.setMensaje("El usuario se modificó con éxito.");
        } else {
            nuevoUsuario.setMensaje("No se pudo modificar el usuario.");
        }

        return mapping.findForward(SUCCESS);
    }
}
