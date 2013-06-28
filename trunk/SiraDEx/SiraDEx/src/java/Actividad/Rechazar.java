/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.Root;
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
public class Rechazar extends DispatchAction {

    /* forward name="success" path="" */
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
        Root.deleteSessions(request, "actividadForm");
        Actividad act = (Actividad) form;
        act.setMensaje(null);
        return mapping.findForward(PAGE);

    }

    public ActionForward reject(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Actividad act = (Actividad) form;

        if (act.getDescripcion().replace("\n", "").length() > 2001) {
            act.setMensaje("Error: El texto debe contener menos de 2000 caracteres.");
            return mapping.findForward(PAGE);
        }

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }


        boolean validacion = act.validar(false, ip, usuario);

        if (validacion) {
            request.getSession().setAttribute("mensajeVal", act.getMensaje());

            act.enviarCorreo(2);
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }
}
