/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

import Clases.Root;
import Clases.TipoActividad;
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
public class Consultar extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS);
        }
        Root.deleteSessions(request, "tipoActividadForm");
        TipoActividad ta = (TipoActividad) form;

        int idTA = ta.getIdTipoActividad();
        ta.setTipoActividad();
        ArrayList campos = Clases.Campo.listar(idTA);
        ta.setCampos(campos);

        return mapping.findForward(SUCCESS);
    }
}
