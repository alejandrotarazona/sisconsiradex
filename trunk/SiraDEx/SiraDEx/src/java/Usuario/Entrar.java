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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Clases.Root.deleteSessions(request, "");

        return mapping.findForward(PAGE);
    }

    public ActionForward signinPO(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Usuario user = (Usuario) form;
        String rol = "obrero";
        if (user.setUsuario(rol)) {
            request.getSession().setAttribute("user", user);
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }

    public ActionForward signinEA(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Usuario user = (Usuario) form;
        String rol = "empleado";
        if (user.setUsuario(rol)) {
            request.getSession().setAttribute("user", user);
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }

    public ActionForward signinES(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Usuario user = (Usuario) form;
        String rol = "estudiante";
        if (user.setUsuario(rol)) {
            request.getSession().setAttribute("user", user);
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }

    public ActionForward signinDEx(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Usuario user = (Usuario) form;
        if (user.setUsuarioDEx()) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("permiso", "dex");
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }

    public ActionForward signinProf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Usuario user = (Usuario) form;
        String rol = "profesor";
        if (user.setUsuario(rol)) {
            request.getSession().setAttribute("user", user);
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }
}
