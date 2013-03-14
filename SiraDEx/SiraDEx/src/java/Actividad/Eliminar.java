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
        Actividad act = (Actividad) form;

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String rol = u.getRol();
        ArrayList<Actividad> acts;

        if (act.eliminarActividad()) {
            act.setMensaje("La actividad ha sido eliminada.");

            if (rol.equalsIgnoreCase("WM")) {
                acts = Clases.Actividad.listarActividades();
            } else {
                act.setCreador(u.getUsername());
                acts = act.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", acts);

            int tam = acts.size();
            if (tam > 0) {
                act = acts.get(tam - 1);
                request.setAttribute("campos", act.getCamposValores());
            } else {
                request.setAttribute("acts", null);
            }
            return mapping.findForward(SUCCESS);

        } else {

            if (rol.equalsIgnoreCase("WM")) {
                acts = Clases.Actividad.listarActividades();
            } else {
                acts = act.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", acts);

            return mapping.findForward(FAILURE);
        }
    }
}
