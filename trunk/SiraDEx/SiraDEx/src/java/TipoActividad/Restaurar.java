/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

import Clases.TipoActividad;
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
public class Restaurar extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS);
        }

        TipoActividad t = (TipoActividad) form;
        t.setTipoActividad();

        if (t.restaurarTipoActividad()) {
            request.getSession().setAttribute("mensajeRest", "El Tipo de Actividad '"
                    + t.getNombreTipo() + "' ha sido restaurado con éxito.");
        } else {
            request.getSession().setAttribute("mensajeRest", "Error: No se pudo restaurar el "
                    + "Tipo de Actividad '" + t.getNombreTipo() + "'.");
        }
        return mapping.findForward(SUCCESS);
    }
}
