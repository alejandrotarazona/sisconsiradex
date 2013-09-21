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
    public ActionForward preListUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");

        return new ActionRedirect("/GestionActividades.do?method=listUser");
    }

    public ActionForward listUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS1);
        }

        Clases.Root.deleteSessions(request, "mensajeAct");

        ArrayList<Actividad> acts = Actividad.listarActividadesDeUsuario(u.getUsername());
        String[] grafica = u.actividadesPorTipo();

        request.setAttribute("acts", acts);
        request.setAttribute("graficaNombres", grafica[0]);
        request.setAttribute("graficaCantidad", grafica[1]);

        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }

        return mapping.findForward(SUCCESS1);
    }

    public ActionForward preListAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");

        return new ActionRedirect("/GestionActividades.do?method=listAll");
    }

    public ActionForward listAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS1);
        }

        Clases.Root.deleteSessions(request, "mensajeAct");

        String[] grafica = u.totalActividadesPorTipo();
        ArrayList<Actividad> acts = Actividad.listarActividades();

        request.setAttribute("acts", acts);
        request.setAttribute("graficaNombres", grafica[0]);
        request.setAttribute("graficaCantidad", grafica[1]);

        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }


        return mapping.findForward(SUCCESS1);
    }

    public ActionForward preListDex(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");

        return new ActionRedirect("/GestionActividades.do?method=listDex");
    }

    public ActionForward listDex(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS1);
        }

        Clases.Root.deleteSessions(request, "mensajeVal");

        ArrayList<Actividad> acts = Actividad.listarActividadesDeValidador(u.getRol());

        request.setAttribute("acts", acts);

        if (acts.isEmpty()) {
            request.setAttribute("acts", null);
        }

        return mapping.findForward(SUCCESS2);
    }
}
