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

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }
        Usuario u = (Usuario) form;
        u.setUsuario();
        request.getSession().setAttribute("usuarioNM", u.clone());
        String r = u.getRol();
        if (!r.equals("WM") && !r.equals("profesor") && !r.equals("obrero")
                && !r.equals("estudiante") && !r.equals("empleado")) {
            u.setRolDex(r);
            u.setRol("dex");
        }

        System.out.println("El usuario elegido es: " + u.toString());

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

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }

        Usuario u = (Usuario) form;

        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        String rol = u.getRol();
        String rolDex = u.getRolDex();
        System.out.println("rol " + rol + " rolDex " + rolDex + "----------------");
        if (rol.equals("dex") && !rolDex.equals("")) {
            rol = rolDex;
            u.setRol(rol);
        }
        if (rol.equals("dex")) {
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
            request.getSession().setAttribute("mensajeUsuario",
                    "El rol del Usuario " + u.getUsername() + " se ha modificado a "
                    + u.getRol() + "con éxito.");

            request.getSession().removeAttribute("usuarioForm.rolDex");
            return mapping.findForward(SUCCESS);
        }
        u.setMensaje("Error: No se pudo modificar el rol del usuario.");
        return mapping.findForward(PAGE);
    }
}
