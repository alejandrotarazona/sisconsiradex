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
    public ActionForward listUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Actividad act = new Actividad();
        Clases.Root.deleteSessions(request, "");
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String username = u.getUsername();
        act.setCreador(username);

        ArrayList<Actividad> acts = Actividad.listarActividadesDeUsuario(u.getUsername());
        String[] grafica = u.cantidadActividadesPorTipo();

        request.setAttribute("acts", acts);
        request.setAttribute("graficaNombres", grafica[0]);
        request.setAttribute("graficaCantidad", grafica[1]);

        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }

        return mapping.findForward(SUCCESS1);
    }

    public ActionForward listAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String[] grafica = u.cantidadActividadesPorTipo();
        ArrayList<Actividad> acts = Actividad.listarActividades();

        request.setAttribute("acts", acts);
        request.setAttribute("graficaNombres", grafica[0]);
        request.setAttribute("graficaCantidad", grafica[1]);

        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }


        return mapping.findForward(SUCCESS1);
    }

    public ActionForward listDex(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario user = (Usuario) request.getSession().getAttribute("user");

        Clases.Root.deleteSessions(request, "");

        ArrayList<Actividad> acts = Actividad.listarActividadesDeValidador(user.getRol());

        request.setAttribute("acts", acts);

        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }

        return mapping.findForward(SUCCESS2);
    }
}
