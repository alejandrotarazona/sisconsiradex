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
    private static final String SUCCESS3 = "success3";
    private static final String SUCCESS4 = "success4";

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
        
        ArrayList<Actividad> acts = act.listarActividadesDeUsuario();
 
        request.setAttribute("acts", acts);
        
        int tam = acts.size();
        if (tam > 0) {
            act = acts.get(0);
            request.setAttribute("campos", act.getCamposValores());
        } else {
            request.setAttribute("acts", null);
        }
        
        return mapping.findForward(SUCCESS1);

        
    }

    public ActionForward listAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Clases.Root.deleteSessions(request, "");
        ArrayList<Actividad> a = Actividad.listarActividades();
        
        request.setAttribute("acts", a);
        
        int tam = a.size();
        if (tam > 0) {
            Actividad act = a.get(0);
            request.setAttribute("campos", act.getCamposValores());
        } else {
            request.setAttribute("acts", null);
        }
        
        
        return mapping.findForward(SUCCESS2);
    }

    public ActionForward listType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Actividad a = new Actividad();
        Clases.Root.deleteSessions(request, "");
        ArrayList<Actividad> act = a.listarActividadesDeTipo();

        request.setAttribute("acts", act);
        request.setAttribute("TipoAct", a);
        return mapping.findForward(SUCCESS3);
    }
    
    public ActionForward listDex(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        
        Clases.Root.deleteSessions(request, "");
        ArrayList<Actividad> a = Actividad.listarActividadesDeValidador(user.getRol());
        
        request.setAttribute("acts", a);
        
        int tam = a.size();
        if (tam > 0) {
            Actividad act = a.get(0);
            request.setAttribute("campos", act.getCamposValores());
        } else {
            request.setAttribute("acts", null);
        }    
        
        return mapping.findForward(SUCCESS4);
    }
}
