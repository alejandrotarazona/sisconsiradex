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
public class Eliminar extends org.apache.struts.action.Action {

    /*
     * forward name="success" path=""
     */
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

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        if (t.eliminarTipoActividad(ip, usuario)) {
            request.getSession().setAttribute("mensajeTipo", "El Tipo de Actividad '"
                    + t.getNombreTipo() + "' ha sido eliminado con éxito.");
        } else {
            request.getSession().setAttribute("mensajeTipo", "Error: No se pudo eliminar el "
                    + "Tipo de Actividad '" + t.getNombreTipo() + "'.");
        }
        return mapping.findForward(SUCCESS);
    }
}
