/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.BusquedaActividad;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author alejandro
 */
public class Exportar extends org.apache.struts.action.Action {

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
        
        BusquedaActividad ba = (BusquedaActividad) request.getSession().getAttribute("busquedaActividadForm");
        String s = ba.exportar();
        
        response.reset();
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=\""+"Busqueda.json"+"\"");

        OutputStream os;
        os = response.getOutputStream();
        os.write(s.getBytes());
        os.flush();
        os.close();

        return null;
    }
}
