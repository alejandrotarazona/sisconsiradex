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
import org.apache.struts.action.ActionRedirect;
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
    public ActionForward prePage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");

        return new ActionRedirect("/VerPerfilUsuario.do?method=page");
    }

    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return mapping.findForward(PAGE);
        }

        Usuario u = (Usuario) form;
        u.setMensaje(null);
        u.setUsername(user.getUsername());
        u.setUsuario();

        Clases.Root.deleteSessions(request, "mensajePerfil");

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

        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        if (u.modificar(ip, usuario)) {
            Clases.Root.deleteSessions(request, "");
            request.getSession().setAttribute("mensajePerfil", u.getMensaje());
            return mapping.findForward(SUCCESS);
        }
        request.getSession().setAttribute("mensajePerfil", u.getMensaje());
        return mapping.findForward(PAGE);

    }
}
