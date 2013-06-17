/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author SisCon
 */
public class Validar extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS);
        }
        Actividad act = (Actividad) form;
        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        boolean validacion = act.validar(true, ip, usuario);

        if (validacion) {
            request.getSession().setAttribute("mensajeVal",
                    "La Actividad ha sido validada con éxito.");
            //act.enviarCorreo(3);
            return mapping.findForward(SUCCESS);
        } else {
            request.getSession().setAttribute("mensajeVal",
                    "Error: La Actividad no se pudo validar. Por favor, intente de nuevo.");
            return mapping.findForward(FAILURE);
        }
    }
}
