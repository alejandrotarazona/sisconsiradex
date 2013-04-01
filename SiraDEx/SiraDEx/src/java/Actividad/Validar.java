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
        System.out.println("El validador es: "+validador);
        boolean validacion = act.validar(true);

        ArrayList<Actividad> acts = Actividad.listarActividadesDeValidador(validador);
       
        request.setAttribute("acts", acts);
        
        int tam = acts.size();
        if (tam > 0) {
            act = acts.get(tam - 1);
            request.setAttribute("campos", act.getCamposValores());
        } else {
            request.setAttribute("acts", null);
        } 
        if (validacion) {
            act.setMensaje("La actividad ha sido validada");
            return mapping.findForward(SUCCESS);
        } else {
            act.setMensajeError("Error: La actividad no se pudo validar, intente de nuevo");
            return mapping.findForward(FAILURE);
        }
    }
}
