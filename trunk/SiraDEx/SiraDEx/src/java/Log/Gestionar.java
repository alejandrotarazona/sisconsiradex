/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import Clases.Actividad;
import Clases.Log;
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
public class Gestionar extends DispatchAction {

    /* forward name="success" path="" */
    private static final String PAGE = "page";
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
    public ActionForward failure(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Actividad act = new Actividad();
        Clases.Root.deleteSessions(request, "");
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String username = u.getUsername();
        act.setCreador(username);
        
        ArrayList<Actividad> acts = Actividad.listarActividadesDeUsuario(u.getUsername());
        
        
        request.setAttribute("acts", acts);
        
        
        int tam = acts.size();
        if (tam > 0) {
            act = acts.get(0);
            request.setAttribute("campos", act.getCamposValores());
        } else {
            request.setAttribute("acts", null);
        }
        
        return mapping.findForward(FAILURE);

        
    }

    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Clases.Root.deleteSessions(request, "");
        ArrayList<Log> a = Log.listar();
        
        request.setAttribute("logs", a);
        
        if (a.isEmpty()) {
         
            request.setAttribute("logs", null);
        }
        
        
        return mapping.findForward(PAGE);
    }

    
    public ActionForward success(ActionMapping mapping, ActionForm form,
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
        
        return mapping.findForward(SUCCESS);
    }
}

    


