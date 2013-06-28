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
 * @author Siscon
 */
public class EditarPerfil extends DispatchAction {

    /* forward name="success" path="" */
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

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }
        Clases.Root.deleteSessions(request, "mensajePerfil");
        Usuario u = (Usuario) form;

        Usuario userNM = new Usuario();
        userNM.setUsername(u.getUsername());
        userNM.setUsuario();
        request.getSession().setAttribute("userNM", userNM);

        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }
        Usuario u = (Usuario) form;

        Usuario userNM = (Usuario) request.getSession().getAttribute("userNM");
        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        if (u.modificar(userNM, ip, usuario)) {
            Clases.Root.deleteSessions(request, "");
            request.getSession().setAttribute("mensajePerfil", u.getMensaje());
            return mapping.findForward(SUCCESS);
        }
        request.getSession().setAttribute("mensajePerfil", u.getMensaje());
        return mapping.findForward(PAGE);

    }
}
