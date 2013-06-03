/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.Usuario;
import java.util.ArrayList;
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
        Actividad act = (Actividad) form;
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String validador = user.getRol();
        
        boolean validacion = act.validar(true);

        ArrayList<Actividad> acts = Actividad.listarActividadesDeValidador(validador);

        if (acts.isEmpty()) {
            acts = null;
        }
        request.setAttribute("acts", acts);
        
        if (validacion) {
            act.setMensaje("La Actividad ha sido validada.");
            act.setMensajeError(null);
            //act.enviarCorreo(3);
            return mapping.findForward(SUCCESS);
        } else {
            act.setMensajeError("Error: La Actividad no se pudo validar, intente de nuevo.");
            return mapping.findForward(FAILURE);
        }
    }
}
