/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

import Clases.TipoActividad;
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
public class Restaurar extends org.apache.struts.action.Action {
    
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TipoActividad t = (TipoActividad) form;
        t.setTipoActividad();
        t.setCampos();
        if (t.restaurarTipoActividad()) {
            ArrayList ta = Clases.TipoActividad.listarCorto(false);
            request.setAttribute("tipos", ta);
            return mapping.findForward(SUCCESS);
        }
        
        ArrayList ta = Clases.TipoActividad.listarCorto(false);
        request.setAttribute("tipos", ta);
        return mapping.findForward(FAILURE);

    }
    
}
