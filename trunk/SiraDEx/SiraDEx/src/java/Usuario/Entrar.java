/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Clases.Usuario;
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
public class Entrar extends DispatchAction {
    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String PAGE = "page";

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

    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(PAGE);
    }
    
    public ActionForward inPO(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        String rol = "obrero";
        if (u.setUsuario(rol)){
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
        }
        u.setMensaje("No existe un usuario con el rol "+rol);
        return mapping.findForward(PAGE);
    }
    
    public ActionForward inEA(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        String rol = "empleado";
        if (u.setUsuario(rol)){
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
        }
        u.setMensaje("No existe un usuario con el rol "+rol);
        return mapping.findForward(PAGE);
    }
    
    public ActionForward inES(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        String rol = "estudiante";
        if (u.setUsuario(rol)){
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
        }
        u.setMensaje("No existe un usuario con el rol "+rol);
        return mapping.findForward(PAGE);
    }
    
    
    public ActionForward inDEx(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        if (u.setUsuarioDEx()){
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
        }
        u.setMensaje("No existe un usuario del DEX");
        return mapping.findForward(PAGE);
    }


    public ActionForward inProf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        String rol = "profesor";
        if (u.setUsuario(rol)){
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
        }
        u.setMensaje("No existe un usuario con el rol "+rol);
        return mapping.findForward(PAGE);
    }

}
