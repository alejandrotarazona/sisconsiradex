/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

import Clases.TipoActividad;
import Clases.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author SisCon
 */
public class Listar extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS1 = "success1";
    private static final String SUCCESS2 = "success2";

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
    public ActionForward preListActive(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");

        return new ActionRedirect("/GestionTiposActividad.do?method=listActive");
    }
    
    public ActionForward listActive(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS1);
        }

        Clases.Root.deleteSessions(request, "mensajeTipo");
        ArrayList<TipoActividad> ta;
        String rol = u.getRol();
        if (rol.equals("WM")) {
            String[] atributos = {"activo"};
            Object[] valores = {true};
            ta = Clases.TipoActividad.listarCondicion(atributos, valores);
        } else {
            String[] atributos = {"activo", "validador"};
            Object[] valores = {true, rol};
            ta = Clases.TipoActividad.listarCondicion(atributos, valores);
        }

        if (ta.isEmpty()) {
            ta = null;
        }
        request.setAttribute("tipos", ta);

        return mapping.findForward(SUCCESS1);
    }
    
    public ActionForward listDisable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS2);
        }

        Clases.Root.deleteSessions(request, "mensajeRest");
        ArrayList<TipoActividad> ta;
        String rol = u.getRol();
        if (rol.equals("WM")) {
            String[] atributos = {"activo"};
            Object[] valores = {false};
            ta = Clases.TipoActividad.listarCondicion(atributos, valores);
        } else {
            String[] atributos = {"activo", "validador"};
            Object[] valores = {false, rol};
            ta = Clases.TipoActividad.listarCondicion(atributos, valores);
        }

        if (ta.isEmpty()) {
            ta = null;
        }
        request.setAttribute("tipos", ta);

        return mapping.findForward(SUCCESS2);
    }
}
