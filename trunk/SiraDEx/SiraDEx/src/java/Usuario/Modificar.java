/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Clases.ElementoCatalogo;
import Clases.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

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
        u.setMensaje(null);
        u.setUsuario();

        System.out.println("El usuario elegido es: " + u.toString());
        request.getSession().setAttribute("usuarioNM", u);

        ArrayList<ElementoCatalogo> catalogo;
        catalogo = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        request.getSession().setAttribute("dependencias", catalogo);

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
        Usuario u = (Usuario) form;
        
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        
        String rol = u.getRol();
        String rolDex = u.getRolDex();
        System.out.println("rol " + rol + " rolDex " + rolDex + "----------------");
        if (!rol.equals(rolDex) && !rolDex.equals("")) {
            rol = rolDex;
            u.setRol(rol);
        }
        if (rol.equals("")) {
            u.setMensaje("Error: Debe elegir una Dependencia o Unidad");
            return mapping.findForward(PAGE);
        }

        Usuario usuarioNM = (Usuario) request.getSession().getAttribute("usuarioNM");
        System.out.println("El viejo usuario es: " + usuarioNM.toString());
        u.setUsername(usuarioNM.getUsername());
        u.setUsuario();

        u.setRol(rol);
        System.out.println("El nuevo usuario es: " + u.toString());

        if (u.modificar(usuarioNM, ip, usuario)) {

            ArrayList<Usuario> usrs = Clases.Usuario.listarUsuario();
            request.setAttribute("usuarios", usrs);
            Clases.Root.deleteSessions(request, "");
            u.setMensaje("El rol de " + u.getUsername() + " se modificó a " + u.getRol());
            u.setMensaje("");
            request.getSession().removeAttribute("usuarioForm.rolDex");
            return mapping.findForward(SUCCESS);
        }
        u.setMensaje("Error: No se pudo modificar el usuario");
        return mapping.findForward(PAGE);
    }
}
