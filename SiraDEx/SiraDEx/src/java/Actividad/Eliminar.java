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
 * @author diana
 */
public class Eliminar extends org.apache.struts.action.Action {

    /*
     * forward name="success" path=""
     */
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
        Actividad a = (Actividad) form;

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String rol = u.getRol();
        ArrayList<Actividad> act;

        if (a.eliminarActividad()) {
            a.setMensaje("La actividad ha sido eliminada.");

            if (rol.equalsIgnoreCase("WM")) {
                act = Clases.Actividad.listarActividades();
            } else {
                act = a.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", act);
            return mapping.findForward(SUCCESS);
            
        } else {
            
            if (rol.equalsIgnoreCase("WM")) {
                act = Clases.Actividad.listarActividades();
            } else {
                act = a.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", act);
            
            a.setMensaje("La actividad que desea eliminar no existe.");
            return mapping.findForward(FAILURE);
        }
    }
}
