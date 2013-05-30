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
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author SisCon
 */
public class Rechazar extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE1 = "failure1";
    private static final String PAGE = "page";
        private static final String FAILURE2 = "failure2";

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

            return mapping.findForward(PAGE);

    }

    public ActionForward reject(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Actividad act = (Actividad) form;
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String validador = user.getRol();
        System.out.println("El validador es: " + validador);

      
        ArrayList<Actividad> acts = Actividad.listarActividadesDeValidador(validador);
        request.setAttribute("acts", acts);


        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }
        if (act.validar(false)) {
            act.setMensaje("La actividad ha sido rechazada.");
            act.setMensajeError(null);
            return mapping.findForward(SUCCESS);
        } else {
            act.setMensajeError("Error: La actividad no se pudo rechazar, intente de nuevo");
            return mapping.findForward(FAILURE1);
        }
    }
}
