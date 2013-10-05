/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Clases.Usuario;
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
public class Login extends DispatchAction {

    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String PAGE = "page";

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
        Usuario u = (Usuario) form;
        u.setMensaje(null);

        // Obteniendo usuario del CAS
        String usbid = request.getRemoteUser();
        if (usbid != null) {
            u.setUsername(usbid);
        }

        if (u.esUsuario()) {
            u.setUsuario();
            request.getSession().setAttribute("user", u);
            String rol = u.getRol();
            if (!rol.equals("profesor") && !rol.equals("empleado")
                    && !rol.equals("estudiante") && !rol.equals("obrero")) {
                if (rol.equals("WM")) {
                    request.getSession().setAttribute("permiso", "wm");
                } else {
                    request.getSession().setAttribute("permiso", "dex");
                }
            }
            request.getSession().setAttribute("mensajeAct", "¡Bienvenido al SiraDEx!");
            return mapping.findForward(SUCCESS);
        } else {

            // Obteniendo datos del LDAP
            if (!u.obtenerUsuario()) {
                request.getSession().setAttribute("fallo", u.getMensaje());
            }
            request.getSession().setAttribute("mensajeAct", "Su registro en el SiraDEx se ha realizado con éxito.");
            return mapping.findForward(PAGE);
        }
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        if (u.agregar(ip, usuario)) {
            request.getSession().setAttribute("user", u);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(PAGE);


    }
}
