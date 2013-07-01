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
public class Agregar extends DispatchAction {

    /*
     * forward name="success" path=""
     */
    private static final String PAGE = "page";
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }

        request.getSession().setAttribute("mensajeUsuario", null);
        
        Usuario u = (Usuario) form;
        u.setMensaje(null);
        request.getSession().setAttribute("userAux", u);
        ArrayList<ElementoCatalogo> catalogo;
        catalogo = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        request.getSession().setAttribute("dependencias", catalogo);

        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }

        Usuario u = (Usuario) form;

        String rol = u.getRol();
        String rolDex = u.getRolDex();

        if (rol.equals("dex") && !rolDex.equals("")) {
            rol = rolDex;
            u.setRol(rol);
        }

        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        if (u.agregar(ip, usuario)) {

            request.getSession().removeAttribute("usuarioForm.rolDex");
            request.getSession().setAttribute("mensajeUsuario", u.getMensaje());
            return mapping.findForward(SUCCESS);
        }
        request.getSession().setAttribute("userAux", u);
        return mapping.findForward(PAGE);

    }
}
